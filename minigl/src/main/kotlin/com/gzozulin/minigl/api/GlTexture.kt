package com.gzozulin.minigl.api

import org.lwjgl.opengl.GL11.GL_TEXTURE_BINDING_2D
import org.lwjgl.opengl.GL13.GL_TEXTURE_BINDING_CUBE_MAP
import java.nio.ByteBuffer
import java.nio.ByteOrder

// At least 80, see https://www.khronos.org/registry/OpenGL-Refpages/gl4/html/glActiveTexture.xhtml
private const val MAX_ACTIVE_TEXTURES = 80
private val availableActiveTextures = (0 until MAX_ACTIVE_TEXTURES).toMutableList()

data class GlTexture(val label: String,
                     val target: Int = backend.GL_TEXTURE_2D, val images: List<GlTextureImage> = emptyList(),
                     val minFilter: Int = backend.GL_NEAREST_MIPMAP_LINEAR, val magFilter: Int = backend.GL_LINEAR,
                     val wrapS: Int = backend.GL_REPEAT, val wrapT: Int = backend.GL_REPEAT, val wrapR: Int = backend.GL_REPEAT,
                     internal var handle: Int? = null, internal var unit: Int? = null)

data class GlTextureImage(val target: Int, val width: Int, val height: Int, val pixels: ByteBuffer? = null,
                          val internalFormat: Int = backend.GL_RGBA, val pixelFormat: Int = backend.GL_RGBA,
                          val pixelType: Int = backend.GL_UNSIGNED_BYTE)

private fun glTextureUnitHold(): Int {
    synchronized(availableActiveTextures) {
        check(availableActiveTextures.isNotEmpty())
        return availableActiveTextures.removeFirst()
    }
}

private fun glTextureUnitRelease(unit: Int) {
    synchronized(availableActiveTextures) {
        availableActiveTextures.add(unit)
    }
}

private fun glTextureSwitchToUnit(texture: GlTexture) {
    backend.glActiveTexture(backend.GL_TEXTURE0 + texture.unit!!)
}

private val binding = IntArray(1)
private fun glTextureGetBound(texture: GlTexture): Int {
    glTextureSwitchToUnit(texture)
    when (texture.target) {
        backend.GL_TEXTURE_2D -> backend.glGetIntegerv(GL_TEXTURE_BINDING_2D, binding)
        backend.GL_TEXTURE_CUBE_MAP -> backend.glGetIntegerv(GL_TEXTURE_BINDING_CUBE_MAP, binding)
        else -> error("Unknown GlTexture type! ${texture.label}")
    }
    return binding[0]
}

private fun glTextureBindPrev(texture: GlTexture, callback: Callback) {
    val prev = glTextureGetBound(texture)
    callback.invoke()
    glTextureSwitchToUnit(texture)
    backend.glBindTexture(texture.target, prev)
}

private fun glTextureUpload(texture: GlTexture) {
    check(texture.handle == null) { "GlTexture is already in use! ${texture.label}" }
    texture.handle = backend.glGenTextures()
    texture.unit = glTextureUnitHold()
    glTextureBindPrev(texture) {
        backend.glActiveTexture(backend.GL_TEXTURE0 + texture.unit!!)
        backend.glBindTexture(texture.target, texture.handle!!)
        texture.images.forEach { image ->
            backend.glTexImage2D(image.target, 0, image.internalFormat,
                image.width, image.height, 0, image.pixelFormat, image.pixelType, image.pixels)
        }
        backend.glGenerateMipmap(texture.target)
        backend.glTexParameteri(texture.target, backend.GL_TEXTURE_MIN_FILTER, texture.minFilter)
        backend.glTexParameteri(texture.target, backend.GL_TEXTURE_MAG_FILTER, texture.magFilter)
        backend.glTexParameteri(texture.target, backend.GL_TEXTURE_WRAP_S, texture.wrapS)
        backend.glTexParameteri(texture.target, backend.GL_TEXTURE_WRAP_T, texture.wrapT)
        backend.glTexParameteri(texture.target, backend.GL_TEXTURE_WRAP_R, texture.wrapR)
    }
}

private fun glTextureDelete(texture: GlTexture) {
    backend.glDeleteTextures(texture.handle!!)
    glTextureUnitRelease(texture.unit!!)
    texture.handle = null
    texture.unit = null
}

fun glTextureUse(texture: GlTexture, callback: Callback) {
    glTextureUpload(texture)
    callback.invoke()
    glTextureDelete(texture)
}

fun glTextureUse(texture: Collection<GlTexture>, callback: Callback) {
    texture.forEach { glTextureUpload(it) }
    callback.invoke()
    texture.forEach { glTextureDelete(it) }
}

fun glTextureBind(texture: GlTexture, callback: Callback) {
    check(texture.handle != null) { "GlTexture is not used! ${texture.label}" }
    glTextureBindPrev(texture) {
        backend.glActiveTexture(backend.GL_TEXTURE0 + texture.unit!!)
        backend.glBindTexture(texture.target, texture.handle!!)
        callback.invoke()
    }
}

fun glTextureCheckBound(texture: GlTexture) {
    check(texture.handle != null) { "GlTexture is not used!" }
    check(glTextureGetBound(texture) == texture.handle) { "GlTexture is not bound! ${texture.label}" }
}

internal fun glTextureCreate2D(label: String, width: Int, height: Int, pixels: ByteBuffer): GlTexture {
    return GlTexture(label, backend.GL_TEXTURE_2D,
        images = listOf(GlTextureImage(backend.GL_TEXTURE_2D, width, height, pixels)))
}

internal fun glTextureCreate2D(label: String, width: Int, height: Int, pixels: ByteArray): GlTexture {
    val data = ByteBuffer.allocateDirect(width * height * 4).put(pixels)
    return glTextureCreate2D(label, width, height, data)
}

internal fun glTextureCreate2D(width: Int, height: Int, pixels: List<col4>): GlTexture {
    val data = ByteBuffer.allocateDirect(width * height * 4).order(ByteOrder.nativeOrder())
    pixels.forEach {
        data.put((it.x * 255f).toInt().toByte())
        data.put((it.y * 255f).toInt().toByte())
        data.put((it.z * 255f).toInt().toByte())
        data.put((it.w * 255f).toInt().toByte())
    }
    data.position(0)
    return glTextureCreate2D("Manual", width, height, data)
}