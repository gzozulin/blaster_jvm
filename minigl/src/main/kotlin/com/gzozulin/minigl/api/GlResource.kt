package com.gzozulin.minigl.api

abstract class GlResource {
    private var isUsed = false

    private val childResource = mutableListOf<GlResource>()

    open fun addChildren(child: GlResource) {
        childResource.add(child)
    }

    open fun addChildren(vararg children: GlResource) {
        childResource.addAll(children)
    }

    open fun addChildren(children: List<GlResource>) {
        childResource.addAll(children)
    }

    open fun use() {
        childResource.forEach { it.use() }
        if (STRICT_MODE) {
            check(!isUsed) { "Already used!" }
        }
        isUsed = true
    }

    open fun release() {
        isUsed = false
        childResource.reversed().forEach { it.release() }
    }

    open fun checkReady() {
        if (STRICT_MODE) {
            check(isUsed) { "Is not used!" }
        }
    }
}