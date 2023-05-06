package com.lj.generics

import org.junit.Assert.assertEquals
import org.junit.Test

class GenericFunctionTest {

    //region ReadIntegerOnly

    @Test
    fun `ReadIntegerOnly should read an Int and return this Int in String`() {
        /** Arrange **/
        val sut = ReadIntegerOnly()
        val readItem = 42
        val expectedResult = readItem.toString()

        /** Act **/
        val result = sut.read(readItem)

        /** Assert **/
        assertEquals(expectedResult, result)
    }

    //endregion ReadIntegerOnly

    //region ReadStringOnly

    @Test
    fun `ReadStringOnly should read a String and return the item length in a String`() {
        /** Arrange **/
        val sut = ReadStringOnly()
        val readItem = "This message contains 36 characters."
        val expectedResult = "36"

        /** Act **/
        val result = sut.read(readItem)

        /** Assert **/
        assertEquals(expectedResult, result)
    }

    //endregion ReadStringOnly

    //region WriteIntegerOnly

    @Test
    fun `WriteIntegerOnly should write 34`() {
        /** Arrange **/
        val sut = WriteIntegerOnly()
        val expectedResult = 34

        /** Act **/
        val result = sut.write()

        /** Assert **/
        assertEquals(expectedResult, result)
    }

    //endregion WriteIntegerOnly

    //region WriteStringOnly

    @Test
    fun `WriteStringOnly should write a string`() {
        /** Arrange **/
        val sut = WriteStringOnly()
        val expectedResult = "Write only string"

        /** Act **/
        val result = sut.write()

        /** Assert **/
        assertEquals(expectedResult, result)
    }

    //endregion WriteStringOnly

    //region ReadStringWriteInt

    @Test
    fun `ReadStringWriteInt should read a string and write its length`() {
        /** Arrange **/
        val sut = ReadStringWriteInt()
        val readString = "String read."
        val expectedResult = 12

        /** Act **/
        sut.read(readString)
        val result = sut.write()

        /** Assert **/
        assertEquals(expectedResult, result)
    }

    //endregion ReadStringWriteInt
}