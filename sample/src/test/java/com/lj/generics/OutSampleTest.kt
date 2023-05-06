package com.lj.generics

import org.junit.Assert
import org.junit.Test

/**
 * [OutInteger] and [OutString] test.
 */
class OutSampleTest {

    @Test
    fun `OutInteger should write 34`() {
        /** Arrange **/
        val sut = OutInteger()
        val expectedResult = 34

        /** Act **/
        val result = sut.write()

        /** Assert **/
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `OutString should write a string`() {
        /** Arrange **/
        val sut = OutString()
        val expectedResult = "Out string"

        /** Act **/
        val result = sut.write()

        /** Assert **/
        Assert.assertEquals(expectedResult, result)
    }
}