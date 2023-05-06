package com.lj.generics

import org.junit.Assert
import org.junit.Test

/**
 * [InOutSample] tests.
 */
class InOutSampleTest {

    @Test
    fun `ReadStringWriteInt should read a string and write its length`() {
        /** Arrange **/
        val sut = InOutSample()
        val readString = "String read."
        val expectedResult = 12

        /** Act **/
        sut.read(readString)
        val result = sut.write()

        /** Assert **/
        Assert.assertEquals(expectedResult, result)
    }

}