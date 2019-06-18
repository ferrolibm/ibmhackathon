package com.langiappeworkshop.sounddetector

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var startButton: Button? = null
    private var stopButton: Button? = null
    private var tvAmplitude: TextView? = null

    private var recorder: MediaRecorder? = null
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.btnStart)
        stopButton = findViewById(R.id.btnStop)
        tvAmplitude = findViewById(R.id.tvAmplitude)

        getPermissions()

    }

    private fun setUpButtons() {
        startButton?.setOnClickListener { _ ->
            startButton?.isEnabled = false
            stopButton?.isEnabled = true

            //Creating MediaRecorder and specifying audio source, output format, encoder & output format
            recorder = MediaRecorder()
            recorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            recorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            recorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            recorder?.setOutputFile("/dev/null")
            recorder?.prepare()
            recorder?.start()
            handler.post(runnableCode)
        }

        stopButton?.setOnClickListener { _ ->
            startButton?.isEnabled = true
            stopButton?.isEnabled = false
            //stopping recorder
            recorder?.stop()
            recorder?.release()
            recorder = null
            handler.removeCallbacks(runnableCode)
        }
    }

    private fun getPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this, /*new String[]*/ Array(1) { Manifest.permission.RECORD_AUDIO }, 0)

        } else {
            setUpButtons()
        }
    }

    fun getAmplitude(): Int? {
        return if (recorder != null) {
            recorder?.maxAmplitude
        } else {
            0
        }
    }

    private val runnableCode = object : Runnable {
        override fun run() {
            val amp = getAmplitude()
            Log.d("#########", "Amplitude:$amp")
            tvAmplitude?.text = amp.toString()
            handler.postDelayed(this, 1000)
        }
    }
}
