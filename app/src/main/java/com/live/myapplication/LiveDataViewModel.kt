package com.live.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class LiveDataViewModel(milli: Long) : ViewModel() {
    private val mDelayTime = MutableLiveData<Long>()
    private val timer: Timer
    val delayTime: LiveData<Long>
        get() = mDelayTime

    public override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    init {
        timer = Timer()

        // Update the elapsed time every second.
        timer.schedule(object : TimerTask() {
            //scheduleAtFixedRate
            override fun run() {
                val cur = System.currentTimeMillis()
                mDelayTime.postValue(cur)
            }
        }, 0, milli)
    }
}