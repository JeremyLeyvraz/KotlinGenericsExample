package com.lj.generics

import org.junit.Assert
import org.junit.Test

/**
 * [WhereNumber] tests.
 */
class WhereSampleTest {

    @Test
    fun `WhereNumberIsInt should get the length of the String version of an Int`() {
        /** Arrange **/
        val sut = WhereNumberIsInt()
        val item = 1234
        val expectedResult = 4

        /** Act **/
        val result = sut.length(item)

        /** Assert **/
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `WhereNumberIsDouble should get the length of the String version of a Double`() {
        /** Arrange **/
        val sut = WhereNumberIsDouble()
        val item = 1234.5678
        val expectedResult = 9

        /** Act **/
        val result = sut.length(item)

        /** Assert **/
        Assert.assertEquals(expectedResult, result)
    }
}