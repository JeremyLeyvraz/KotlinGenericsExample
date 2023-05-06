package com.lj.generics

/**
 * Interface to declare the type [T] read-only.
 */
interface In<in T> {

    /**
     * Read an item and return a string.
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
 * [Int] can be read only.
 */
class InInteger: In<Int> {

    /**
     * Read an [Int] and display its value in [String].
     *
     * @param item [Int] read
     *
     * @return [item] in [String]
     */
    override fun read(item: Int) = item.toString()
}

/**
 * [String] can be read only.
 */
class InString: In<String> {

    /**
     * Read a [String] and display its number of characters.
     *
     * @param item [String] read
     *
     * @return A [String] "Read [item]"
     */
    override fun read(item: String) = "${item.length}"
}