package com.gzozulin.proj

private const val FLAG = false

fun highlevelFunction() {
    if (FLAG) {
        println("Doing it high-level")
    }
}

class UtilityClass {
    fun internalFunction() {
        highlevelFunction()
    }
}

class MainClass(val hereisanUglyCtor: Int,
                val multiline: Boolean,
                val ofcourse: Char) {

    private val internalFlag = true
    private val internalValue = UtilityClass()

    fun originFunction() {
        // Comments are welcome!
        if (internalFlag) {
            internalValue.internalFunction()
        }
    }
}