package com.gzozulin.minigl.api2

import com.gzozulin.minigl.api.backend

data class GlProgram(internal val vertexShader: GlShader, internal val fragmentShader: GlShader,
                     internal var handle: Int? = null)

private fun glCreateProgram(program: GlProgram) {
    program.handle = backend.glCreateProgram()
    check(program.vertexShader.type == backend.GL_VERTEX_SHADER)
    check(program.fragmentShader.type == backend.GL_FRAGMENT_SHADER)
    backend.glAttachShader(program.handle!!, program.vertexShader.handle!!)
    backend.glAttachShader(program.handle!!, program.vertexShader.handle!!)
    backend.glLinkProgram(program.handle!!)
    val isLinked = backend.glGetProgrami(program.handle!!, backend.GL_LINK_STATUS)
    if (isLinked == backend.GL_FALSE) {
        error(backend.glGetProgramInfoLog(program.handle!!))
    }
}

internal fun glUseProgram(program: GlProgram, callback: Callback) {
    check(program.handle == null) { "GlProgram is already in use!" }
    glUseShader(program.vertexShader) {
        glUseShader(program.fragmentShader) {
            glCreateProgram(program)
            callback.invoke()
            backend.glDeleteProgram(program.handle!!)
            program.handle = null
        }
    }
}

private var currBinding: Int? = null
internal fun glBindProgram(program: GlProgram, callback: Callback) {
    check(program.handle != null) { "GlProgram is not used!" }
    val prev = currBinding
    backend.glUseProgram(program.handle!!)
    currBinding = program.handle
    callback.invoke()
    backend.glUseProgram(prev ?: 0)
    currBinding = prev
}

internal fun glCheckProgramBound() {
    check(currBinding != null) { "No GlProgram is bound!" }
}

internal fun glDrawTriangles(mesh: GlMesh) {
    glCheckProgramBound()
    glCheckMeshBound()
    backend.glDrawElements(backend.GL_TRIANGLES, mesh.indicesCnt, backend.GL_UNSIGNED_INT, 0)
}

internal fun glSendUniform() {
    // check everything is bound
}

internal fun glSendTexture() {
    // check everything is bound
}

internal fun testGlProgram() {
    //glUseProgram(GlProgram(GlShader()))
}

//root (varrrying, uniform, constant) > expressions