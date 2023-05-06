package com.lj.coroutines

import android.util.Log
import kotlinx.coroutines.*

class CoroutineManager(
    private val dispatcherToUse : CoroutineDispatcher = Dispatchers.Unconfined,
    private val timeoutInMillis : Long = 3000
) {

    private val scopeDispatchersIO: CoroutineScope = CoroutineScope(dispatcherToUse)
    private val logTag = CoroutineManager::class.simpleName

    //region Fire and forget methods

    fun runFireAndForget(method: suspend () -> Unit){
        log("FireAndForget: Method - Start")
        scopeDispatchersIO.launch() {
            log("FireAndForget: Coroutine - Start")
            method()
            log("FireAndForget: Coroutine - End")
        }
        log("FireAndForget: Method - End")
    }

    fun runFireAndForgetWithTimeout(method: suspend () -> Unit){
        log("FireAndForgetWithTimeout: Method - Start")
        scopeDispatchersIO.launch {
            try {
                log("FireAndForgetWithTimeout: Coroutine - Start")
                withTimeout(timeoutInMillis){
                    method()
                }
            }
            finally {
                log("FireAndForgetWithTimeout: Coroutine - End")
            }
        }
        log("FireAndForgetWithTimeout: Method - End")
    }

    fun runFireAndForgetWithTimeoutWithCallbackWhenError(method: suspend () -> Unit, callback: suspend () -> Unit){
        log("FireAndForgetWithTimeoutWithCallbackWhenError: Method - Start")
        scopeDispatchersIO.launch {
            try {
                log("FireAndForgetWithTimeoutWithCallbackWhenError: Coroutine - Start")
                withTimeout(timeoutInMillis){
                    method()
                }
                log("FireAndForgetWithTimeoutWithCallbackWhenError: Coroutine - End -- Impossible to reach")
            }
            catch(e: TimeoutCancellationException) {
                log("FireAndForgetWithTimeoutWithCallbackWhenError: Timeout callback - Start")
                callback()
                log("FireAndForgetWithTimeoutWithCallbackWhenError: Timeout callback - End")
            }
        }
        log("FireAndForgetWithTimeoutWithCallbackWhenError: Method - End")
    }

    //endregion

    //region Fire and wait methods

    fun runFireAndWait(method: suspend () -> Unit) {
        log("FireAndWait: Method - Start")
        runBlocking {
            scopeDispatchersIO.launch {
                log("FireAndWait: Coroutine - Start")
                method()
                log("FireAndWait: Coroutine - End")
            }.join()
        }
        log("FireAndWait: Method - End")
    }

    fun <T> runFireAndWaitResult(method: suspend () -> T): T? {
        log("FireAndWaitResult: Method - Start")
        var result: T? = null
        runBlocking {
            scopeDispatchersIO.launch {
                log("FireAndWaitResult: Coroutine - Start")
                result = method()
                log("FireAndWaitResult: Coroutine - End")
            }.join()
        }
        log("FireAndWaitResult: Method - End")
        return result
    }

    fun runFireAndWaitActionWithFireAndForgetCallback(method: suspend () -> Unit, callback: suspend () -> Unit) {
        log("FireAndWaitWithFireAndForgetCallback: Method - Start")
        runBlocking {
            scopeDispatchersIO.launch {
                log("FireAndWaitWithFireAndForgetCallback: Coroutine - Start")
                method()
                log("FireAndWaitWithFireAndForgetCallback: Coroutine - End")
            }.join()
        }
        scopeDispatchersIO.launch {
            log("FireAndWaitWithFireAndForgetCallback: Callback - Start")
            callback()
            log("FireAndWaitWithFireAndForgetCallback: Callback - End")
        }
        log("FireAndWaitWithFireAndForgetCallback: Method - End")
    }

    //endregion

    //region Test methods

    fun log(message: String){
        Log.e(logTag, message)
    }

    suspend fun simulateAction(){
        for(i in 0..10) {
            log("Complete... ${i*10}%")
            delay(500L)
        }
    }

    //endregion Test methods
}
