package com.langiappeworkshop.autosafedemo

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var startButton: Button? = null
    private var stopButton: Button? = null
    private var tvAmplitude: TextView? = null

    private lateinit var soundDetector: SoundDetector
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundDetector = SoundDetector(this)

        startButton = findViewById(R.id.btnStart)
        stopButton = findViewById(R.id.btnStop)
        tvAmplitude = findViewById(R.id.tvAmplitude)

        startButton?.isEnabled = true
        stopButton?.isEnabled = false

        if (soundDetector.getPermissions()) {
            setUpButtons()
        }

    }

    private fun setUpButtons() {

        startButton?.setOnClickListener { _ ->
            startButton?.isEnabled = false
            stopButton?.isEnabled = true
            soundDetector.startRecording(handler, runnableCode)
        }

        stopButton?.setOnClickListener { _ ->
            startButton?.isEnabled = true
            stopButton?.isEnabled = false
            //stopping recorder
            soundDetector.stopRecording(handler, runnableCode)
        }
    }

    private val runnableCode = object : Runnable {
        override fun run() {
            val amp = soundDetector.getAmplitude()
            Log.d("#########", "Amplitude:$amp")
            tvAmplitude?.text = amp.toString()
            handler.postDelayed(this, 1000)
        }
    }
}
