package com.lj.generics

/**
 * Interface for [Number].
 */
interface WhereNumber<in T> where T: Number {
    /**
     * Calculates the length of [item] of type [T].
     *
     * @param item Calculates the length of this item of type [T].
     *
     * @return The length of this item of type [T].
     */
    fun length(item: T): Int
}

/**
 * Implementation of [WhereNumber] with [Int].
 */
class WhereNumberIsInt : WhereNumber<Int> {

    /**
     * Returns the length of the [String] version of the [Int] parameter.
     *
     * @param item The [Int] to calculate the [String] length.
     *
     * @return The length of the [String] version of the [Int].
     */
    override fun length(item: Int): Int = item.toString().length
}

/**
 * Implementation of [WhereNumber] with [Double].
 */
class WhereNumberIsDouble : WhereNumber<Double> {

    /**
     * Returns the length of the [String] version of the [Double] parameter.
     *
     * @param item The [Double] to calculate the [String] length.
     *
     * @return The length of the [String] version of the [Double].
     */
    override fun length(item: Double): Int = item.toString().length
}
