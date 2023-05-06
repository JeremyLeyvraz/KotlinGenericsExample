package com.lj.coroutines

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import kotlin.random.Random

abstract class BaseTest {

    private val forceError = false
    private val forceRandom = false

    @After
    fun forceAssertFailed(){
        if(forceError){
            assertEquals(false, forceError)
        } else if(forceRandom){
            val random = Random.nextInt(1, 101)
            assertTrue(50 <= random)
        }
    }

}