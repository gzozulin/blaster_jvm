package com.gzozulin.proj

import com.gzozulin.minigl.api.black
import com.gzozulin.minigl.api.col3
import com.gzozulin.minigl.api.glClear
import org.kodein.di.instance

class Controller(private var current: State = StateIdle()) {

    private var next: State? = null

    fun keyPressed(key: Int, pressed: Boolean) {
        current.onKey(key, pressed)
    }

    fun frame() {
        if (next != null) {
            current.onLeave()
            current = next!!
            current.onEnter()
            next = null
        }
        current.onFrame()
    }

    fun switch(next: State) {
        this.next = next
    }
}

open class State {
    open fun onEnter() {}
    open fun onLeave() {}

    open fun onFrame() {}

    open fun onKey(key: Int, pressed: Boolean) {}
}

class StateIdle : State() {
    private val controller: Controller by ProjectorApp.injector.instance()

    override fun onFrame() {
        super.onFrame()
        glClear(col3().black())
    }

    override fun onKey(key: Int, pressed: Boolean) {
        super.onKey(key, pressed)
        if (!pressed) {
            controller.switch(StateCozyRoomIntro())
        }
    }
}

class StateCozyRoomIntro: State() {
    private val controller: Controller by ProjectorApp.injector.instance()
    private val scene: SceneCozyRoom by ProjectorApp.injector.instance()

    override fun onEnter() {
        super.onEnter()
        scene.fadeIn()
    }

    override fun onFrame() {
        scene.tickCamera()
        scene.renderScene()
        scene.renderCrossFade()
    }

    override fun onKey(key: Int, pressed: Boolean) {
        super.onKey(key, pressed)
        if (!pressed) {
            controller.switch(StateCozyRoomTyping())
        }
    }
}

class StateCozyRoomTyping: State() {
    private val scene: SceneCozyRoom by ProjectorApp.injector.instance()
    private val mechanicPlayback: MechanicPlayback by ProjectorApp.injector.instance()

    override fun onFrame() {
        super.onFrame()
        mechanicPlayback.updateSpans()
        scene.tickCamera()
        scene.prepareCode()
        scene.renderScene()
        scene.renderCode()
    }

    override fun onKey(key: Int, pressed: Boolean) {
        super.onKey(key, pressed)
        if (!pressed) {
            mechanicPlayback.proceed()
        }
    }
}