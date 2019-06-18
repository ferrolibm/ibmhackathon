package com.langiappeworkshop.autosafedemo

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var startButton: Button? = null
    private var stopButton: Button? = null
    private var sendButton: Button? = null
    private var tvAmplitude: TextView? = null
    private var etPhone: EditText? = null
    private var etMessage: EditText? = null

    private lateinit var soundDetector: SoundDetector
    private lateinit var sMSSender: SendSMS
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundDetector = SoundDetector(this)
        sMSSender = SendSMS(this)

        startButton = findViewById(R.id.btnStart)
        stopButton = findViewById(R.id.btnStop)
        sendButton = findViewById(R.id.btnSend)
        tvAmplitude = findViewById(R.id.tvAmplitude)
        etPhone = findViewById(R.id.etPhone)
        etMessage = findViewById(R.id.etMessage)

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

        sendButton?.setOnClickListener { _ ->
            val phone = etPhone?.text.toString()
            val message = etMessage?.text.toString()
            sMSSender.sendSMS(phone, message)
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
