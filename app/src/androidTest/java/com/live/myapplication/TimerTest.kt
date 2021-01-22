package com.test.timer

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.live.myapplication.LiveDataViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class TimerTest {
    private val lock = CountDownLatch(1)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.test.timer", appContext.packageName)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testTimer() {
        val DELAY_SEC: Long = 1000
        val viewModel = LiveDataViewModel(DELAY_SEC)
        val st = System.currentTimeMillis()
        lock.await(DELAY_SEC + 1000, TimeUnit.MILLISECONDS)
        val et = viewModel.delayTime.value
        val gap = et!! - st
        assert(gap > DELAY_SEC)
        viewModel.onCleared()
    }
}