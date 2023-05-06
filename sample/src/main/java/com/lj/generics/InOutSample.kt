package com.lj.generics

/**
 * [String] can be read only and [Int] can be write only.
 */
class InOutSample: In<String>, Out<Int> {

    /**
     * [String] read.
     */
    private var readMessage : String = ""

    /**
     * Read [item] and return [item]
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