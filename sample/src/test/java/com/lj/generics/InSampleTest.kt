package com.lj.generics

import org.junit.Assert
import org.junit.Test

/**
 * [InInteger] and [InString] tests.
 */
class InSampleTest {

    @Test
    fun `InInteger should read an Int and return this Int in String`() {
        /** Arrange **/
        val sut = InInteger()
        val readItem = 42
        val expectedResult = readItem.toString()

        /** Act **/
        val result = sut.read(readItem)

        /** Assert **/
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `InString should read a String and return the item length in a String`() {
        /** Arrange **/
        val sut = InString()
        val readItem = "This message contains 36 characters."
        val expectedResult = "36"

        /** Act **/
        val result = sut.read(readItem)

        /** Assert **/
        Assert.assertEquals(expectedResult, result)
    }
}