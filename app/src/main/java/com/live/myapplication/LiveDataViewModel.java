package com.live.myapplication;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LiveDataViewModel extends ViewModel {
    private MutableLiveData<Long> mDelayTime = new MutableLiveData<Long>();

    private final Timer timer;

    public LiveDataViewModel(long milli) {
        timer = new Timer();

        // Update the elapsed time every second.
        timer.schedule(new TimerTask() {//scheduleAtFixedRate
            @Override
            public void run() {
                Long cur = System.currentTimeMillis();
                mDelayTime.postValue(cur);
            }
        }, 0, milli);


    }

    public LiveData<Long> getDelayTime() {
        return mDelayTime;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        timer.cancel();
    }
}
