package com.lj.generics

/**
 * Interface to declare the type [T] in write-only.
 */
interface Out<out T> {

    /**
     * Write something of type [T].
     *
     * @return An item of type [T]
     */
    fun write(): T

    // This line not compiles !
    // Type parameter T is declared as 'out' but occurs in 'in' position in type T
    // fun notCompile(message: T)
}

/**
 * [Int] can be write only.
 */
class OutInteger: Out<Int> {

    /**
     * Write 34.
     *
     * @return 34
     */
    override fun write(): Int = 34
}

/**
 * [String] can be write only.
 */
class OutString: Out<String> {

    /**
     * Write a [String].
     *
     * @return The "Write only string" string.
     */
    override fun write(): String = "Out string"
}
