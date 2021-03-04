package com.gzozulin.minigl.gl

import com.gzozulin.minigl.scene.Version
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.ByteBuffer

private const val WIDTH: Int = 800
private const val HEIGHT: Int = 600

private const val WIN_X: Int = 0
private const val WIN_Y: Int = 0

private const val BPP = 4 // RGBA, 1 byte each

private val keyCallbackInternal = object : GLFWKeyCallback() {
    override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
        if (key == GLFW_KEY_ESCAPE) {
            glfwSetWindowShouldClose(window, true)
        }
    }
}

class GlCapturer {
    var handle = NULL

    val frameBuffer: ByteBuffer by lazy {
        ByteBuffer.allocateDirect(width * height * BPP)
    }

    private var fps = 0
    private var last = System.currentTimeMillis()

    val width: Int = WIDTH
    val height: Int = HEIGHT

    private fun updateFps() {
        fps++
        val current = System.currentTimeMillis()
        if (current - last >= 1000L) {
            glfwSetWindowTitle(handle, "Blaster! $fps fps")
            last = current
            fps = 0
        }
    }

    fun create(onCreated: () -> Unit) {
        glfwSetErrorCallback { error, description -> error("$error, $description") }
        check(glfwInit())
        val result = glfwCreateWindow(width, height, "Blaster!", NULL, handle)
        glfwSetWindowPos(result, WIN_X, WIN_Y)
        glfwSetKeyCallback(result, keyCallbackInternal)
        glfwMakeContextCurrent(result)
        glfwSwapInterval(1)
        handle = result
        GL.createCapabilities();
        onCreated.invoke()
        glfwDestroyWindow(handle)
    }

    fun show(onFrame: () -> Unit, onBuffer: () -> Unit) {
        check(handle != NULL) { "Window is not yet created!" }
        glCheck { backend.glViewport(0, 0, width, height) }
        glfwShowWindow(handle)
        while (!glfwWindowShouldClose(handle)) {
            onFrame.invoke()
            glfwSwapBuffers(handle)
            copyWindowBuffer()
            onBuffer.invoke()
            glfwPollEvents()
            updateFps()
            GlProgram.stopComplaining()
        }
    }

    private fun copyWindowBuffer() {
        GL11.glReadBuffer(GL11.GL_FRONT)
        GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, frameBuffer)
    }
}

private val capturer = GlCapturer()

var once = Version()

fun main() {
    capturer.create {
        capturer.show(
            onFrame = {
                glClear(col3(1f, 1f, 0f))
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT) // clear the framebuffer
            },
            onBuffer = {
                if (once.check()) {
                    println("RGBA: ${capturer.frameBuffer[0]}, ${capturer.frameBuffer[1]}, ${capturer.frameBuffer[2]}, ${capturer.frameBuffer[3]}")
                }
            })
    }
}