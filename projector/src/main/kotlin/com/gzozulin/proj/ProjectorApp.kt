package com.gzozulin.proj

import com.gzozulin.minigl.api.GlWindow
import org.lwjgl.glfw.GLFW

private val window = GlWindow()
private val capturer = Capturer(window)

private val projectorModel = ProjectorModel()
private val projectorView = ProjectorView(projectorModel)
private val projectorController = ProjectorController(projectorModel, projectorView)

fun main() {
    window.create(
        isFullscreen = true, isHoldingCursor = false, isMultisampling = true) {
        window.keyCallback = { key, pressed ->
            if (key == GLFW.GLFW_KEY_R && pressed) {
                capturer.isCapturing = !capturer.isCapturing
            }
            projectorController.keyPressed(key, pressed)
        }
        projectorView.use {
            justFrame()
        }
    }
}

private fun frameWithCapture() {
    capturer.capture {
        window.show {
            projectorController.onFrame()
            window.copyWindowBuffer()
            capturer.frame()
        }
    }
}

private fun justFrame() {
    window.show {
        projectorController.onFrame()
    }
}