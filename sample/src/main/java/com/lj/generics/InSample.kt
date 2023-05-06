package com.lj.generics

/**
 * Interface to declare the type [T] read-only.
 */
interface In<in T> {

    /**
     * Reads an item and returns a string.
     *
     * @param item An item of type [T]
     *
     * @return A string
     */
    fun read(item: T): String

    // This line not compiles !
    // Type parameter T is declared as 'in' but occurs in 'out' position in type T
    //fun notCompile(): T
}

/**
 * [Int] is read-only.
 */
class InInteger: In<Int> {

    /**
     * Reads an [Int] and displays it in [String].
     *
     * @param item [Int] read
     *
     * @return [item] in [String]
     */
    override fun read(item: Int) = item.toString()
}

/**
 * [String] is read-only.
 */
class InString: In<String> {

    /**
     * Reads a [String] and displays its number of characters.
     *
     * @param item A [String]
     *
     * @return The [item] length
     */
    override fun read(item: String) = "${item.length}"
}