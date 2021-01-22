package com.test.timer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.live.myapplication.LiveDataViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TimerTest {
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.test.timer", appContext.getPackageName());
    }


    @Test
    public void testTimer() throws InterruptedException {

        long DELAY_SEC = 1000;
        LiveDataViewModel viewModel = new LiveDataViewModel(DELAY_SEC);

        Long st = System.currentTimeMillis();

        lock.await(DELAY_SEC + 1000, TimeUnit.MILLISECONDS);

        Long et = viewModel.getDelayTime().getValue();
        long gap = et - st;
        assert(gap > DELAY_SEC);

        viewModel.onCleared();

    }

}