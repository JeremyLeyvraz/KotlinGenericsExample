package com.lj.coroutines

import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@ExperimentalCoroutinesApi
@RunWith(Parameterized::class)
class CoroutineManagerTests(dummyParameter: Int) : BaseTest() {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setDispatcher() {
        // Set Coroutine Dispatcher.
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun resetDispatcher() {
        Dispatchers.resetMain()
    }

    //region runFireAndForget

    @Test
    fun runFireAndForget_whenActionIsFire_thenVerifyActionCompletionAfterLongTime() = runTest {
        /** Arrange **/
        val actionDurationInMillis : Long = 10000 // 10 seconds
        val testDurationInMillis : Long = 60000 // 60 seconds
        var isActionComplete = false
        val expectedActionCompleted = true // Action will be done
        val sut = CoroutineManager(testDispatcher)

        /** Act **/
        sut.runFireAndForget {
            delay(actionDurationInMillis) // Virtual time: wait 'actionDurationInMillis' milliseconds
            isActionComplete = true
        }
        // Advance virtual time: 'testDurationInMillis' milliseconds -> Action completed
        testScheduler.apply { advanceTimeBy(testDurationInMillis); runCurrent() }

        /** Assert **/
        assertEquals(expectedActionCompleted, isActionComplete)
    }

    @Test
    fun runFireAndForget_whenIsFire_thenVerifyIsForget() = runTest {
        /** Arrange **/
        val actionDurationInMillis : Long = 60000 // 60 seconds
        val testDurationInMillis : Long = 10000 // 10 seconds
        var isActionComplete = false
        val expectedActionNotCompleted = false // Action will not be done
        var isEnteredInThread = false // Not entered in thread
        val expectedEnteredInThread = true // Will be entered in thread
        val sut = CoroutineManager(testDispatcher)

        /** Act **/
        sut.runFireAndForget {
            isEnteredInThread = true
            delay(actionDurationInMillis) // Virtual time: wait 'actionDurationInMillis' milliseconds
            isActionComplete = true
        }
        // Advance virtual time: 'testDurationInMillis' milliseconds -> Action not completed yet
        testScheduler.apply { advanceTimeBy(testDurationInMillis); runCurrent() }

        /** Assert **/
        assertEquals(expectedEnteredInThread, isEnteredInThread)
        assertEquals(expectedActionNotCompleted, isActionComplete)
    }

    //endregion runFireAndForget

    //region

    @Test
    fun runFireAndForgetWithTimeout_whenIsFireAndTimeoutReach_thenVerifyMethodIsNotComplete() = runTest {
        /** Arrange **/
        val actionDurationInMillis : Long = 30000 // 30 seconds
        val actionTimeoutInMillis : Long = 1000 // 1 second -> Timeout will be reached
        val testDurationInMillis : Long = 60000 // 60 seconds
        var isActionComplete = false
        val expectedActionNotCompleted = false // Action will not be completed
        var isEnteredInThread = false // Not entered in thread
        val expectedEnteredInThread = true // Will be entered in thread
        val sut = CoroutineManager(testDispatcher, actionTimeoutInMillis)

        /** Act **/
        sut.runFireAndForgetWithTimeout {
            isEnteredInThread = true
            delay(actionDurationInMillis) // Virtual time: wait 'actionDurationInMillis' milliseconds -> Timeout reached
            isActionComplete = true
        }
        // Advance virtual time: 'testDurationInMillis' milliseconds -> Action completed with timeout
        testScheduler.apply { advanceTimeBy(testDurationInMillis); runCurrent() }

        /** Assert **/
        assertEquals(expectedEnteredInThread, isEnteredInThread)
        assertEquals(expectedActionNotCompleted, isActionComplete)
    }

    @Test
    fun runFireAndForgetWithTimeout_whenIsFireAndTimeoutNotReach_thenVerifyMethodIsComplete() = runTest {
        /** Arrange **/
        val actionDurationInMillis : Long = 30000 // 30 seconds
        val actionTimeoutInMillis : Long = 60000 // 60 second -> Timeout not reached
        val testDurationInMillis : Long = 40000 // 40 seconds
        var isActionComplete = false
        val expectedActionCompleted = true // Action will be completed
        var isEnteredInThread = false // Not entered in thread
        val expectedEnteredInThread = true // Will be entered in thread
        val sut = CoroutineManager(testDispatcher, actionTimeoutInMillis)

        /** Act **/
        sut.runFireAndForgetWithTimeout {
            isEnteredInThread = true
            delay(actionDurationInMillis) // Virtual time: wait 'actionDurationInMillis' milliseconds -> Timeout not reached
            isActionComplete = true
        }
        // Advance virtual time: 'testDurationInMillis' milliseconds -> Action is completed without timeout
        testScheduler.apply { advanceTimeBy(testDurationInMillis); runCurrent() }

        /** Assert **/
        assertEquals(expectedEnteredInThread, isEnteredInThread)
        assertEquals(expectedActionCompleted, isActionComplete)
    }

    @Test
    fun runFireAndForgetWithTimeout_whenIsFire_thenVerifyIsForget() = runTest {
        /** Arrange **/
        val actionDurationInMillis : Long = 30000 // 30 seconds
        val actionTimeoutInMillis : Long = 60000 // 60 second -> Timeout not reached
        val testDurationInMillis : Long = 10000 // 10 seconds
        var isActionComplete = false
        val expectedActionCompleted = false // Action will not be completed
        var isEnteredInThread = false // Not entered in thread
        val expectedEnteredInThread = true // Will be entered in thread
        val sut = CoroutineManager(testDispatcher, actionTimeoutInMillis)

        /** Act **/
        sut.runFireAndForgetWithTimeout {
            isEnteredInThread = true
            delay(actionDurationInMillis) // Virtual time: wait 'actionDurationInMillis' milliseconds -> Timeout not reached
            isActionComplete = true
        }
        // Advance virtual time: 'testDurationInMillis' milliseconds -> Action not completed yet
        testScheduler.apply { advanceTimeBy(testDurationInMillis); runCurrent() }

        /** Assert **/
        assertEquals(expectedEnteredInThread, isEnteredInThread)
        assertEquals(expectedActionCompleted, isActionComplete)
    }

    //endregion

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}/10000")
        fun data(): Iterable<Int> {
            val res = arrayListOf<Int>()
            for(i in 1..1000){res.add(i)}
            return res
        }
    }
}