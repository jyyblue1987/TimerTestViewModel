package com.live.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var viewModel: LiveDataViewModel? = null
    var editInterval: EditText? = null
    var btnStart: Button? = null
    var m_bStartFlag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editInterval = findViewById<View>(R.id.edit_interval) as EditText
        btnStart = findViewById<View>(R.id.btn_start) as Button
        btnStart!!.setOnClickListener(this)
    }

    private fun subscribe() {
        val delayTimeObserver: Observer<Long?> = Observer<Long?> { aLong ->
            val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm:ss")
            val resultdate = Date(aLong!!)
            val newText = sdf.format(resultdate)
            (findViewById<View>(R.id.tm_result) as TextView).text = newText
        }
        viewModel!!.delayTime.observe(this, delayTimeObserver)
    }

    override fun onClick(view: View) {
        if (m_bStartFlag) {
            var ninterval = 0
            ninterval = try {
                editInterval!!.text.toString().toInt()
            } catch (er: Exception) {
                return
            }
            m_bStartFlag = false
            editInterval!!.isEnabled = false
            btnStart!!.text = "Stop"
            viewModel = LiveDataViewModel(ninterval.toLong())
            subscribe()
        } else {
            btnStart!!.text = "Start"
            m_bStartFlag = true
            editInterval!!.isEnabled = true
            viewModel!!.onCleared()
        }
    }
}