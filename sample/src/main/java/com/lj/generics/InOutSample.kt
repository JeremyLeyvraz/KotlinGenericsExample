package com.lj.generics

/**
 * [String] is read-only and [Int] is write-only.
 */
class InOutSample: In<String>, Out<Int> {

    /**
     * The last [String] read.
     */
    private var lastItemRead : String = ""

    /**
     * Reads [item] and returns [item].
     */
    override fun read(item: String): String {
        lastItemRead = item
        return item
    }

    /**
     * Returns the length of the last item read.
     *
     * @return The length of the last item read.
     */
    override fun write(): Int = lastItemRead.length
}