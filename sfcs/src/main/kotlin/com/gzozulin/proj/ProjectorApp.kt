package com.gzozulin.proj

import com.gzozulin.minigl.api.GlWindow
import com.gzozulin.minigl.api.glUse

// todo: scenario to nodes
// todo: center on function (stop - start)/2
// todo: basic scene arrangement
// todo: example project + video
// todo: off-screen MSAA for code

private val window = GlWindow()

private val projectorModel = ProjectorModel()
private val projectorView = ProjectorView(projectorModel, window.width, window.height)
private val projectorController = ProjectorController(projectorModel, projectorView)

fun main() {
    window.create(isFullscreen = true) {
        window.keyCallback = { key, pressed ->
            projectorController.keyPressed(key, pressed)
        }
        window.resizeCallback = { width, height ->
            projectorView.resize(width, height)
        }
        glUse(projectorView) {
            window.show {
                projectorController.onFrame()
            }
        }
    }
}