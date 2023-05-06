package com.lj.generics

/**
 * Interface to declare the type [T] in read-only.
 */
interface ReadOnly<in T> {

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
 * Interface to declare the type [T] in write-only.
 */
interface WriteOnly<out T> {

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
 * [Int] can be read only.
 */
class ReadIntegerOnly: ReadOnly<Int> {

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
class ReadStringOnly: ReadOnly<String> {

    /**
     * Read a [String] and display its number of characters.
     *
     * @param item [String] read
     *
     * @return A [String] "Read [item]"
     */
    override fun read(item: String) = "${item.length}"
}

/**
 * [Int] can be write only.
 */
class WriteIntegerOnly: WriteOnly<Int> {

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
class WriteStringOnly: WriteOnly<String> {

    /**
     * Write a [String].
     *
     * @return The "Write only string" string.
     */
    override fun write(): String = "Write only string"
}

/**
 * [String] can be write only and [Int] can be write only.
 */
class ReadStringWriteInt: ReadOnly<String>, WriteOnly<Int> {

    /**
     * [String] read.
     */
    private var readMessage : String = ""

    /**
     * Read [item] and return "Read: [item]"
     */
    override fun read(item: String): String {
        readMessage = item
        return item
    }

    /**
     * Return the length of the last item read.
     *
     * @return The length of the last item read.
     */
    override fun write(): Int = readMessage.length
}
