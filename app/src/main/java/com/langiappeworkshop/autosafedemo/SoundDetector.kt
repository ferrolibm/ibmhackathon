package com.langiappeworkshop.autosafedemo

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Handler
import android.support.v4.app.ActivityCompat

class SoundDetector(private val activity: Activity) {
    private var recorder: MediaRecorder? = null

    fun getPermissions(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(activity, Array(1) { Manifest.permission.RECORD_AUDIO }, 0)
            false
        } else {
            true
        }
    }

    fun startRecording(handler: Handler, runnableCode: Runnable) {
        //Creating MediaRecorder and specifying audio source, output format, encoder & output format

        // Record to the external cache directory for visibility
        //val fileName = "${activity.externalCacheDir?.absolutePath}/audiorecordtest.3gp"

        recorder = MediaRecorder()
        recorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        recorder?.setOutputFile("/dev/null")//(fileName)
        recorder?.prepare()
        recorder?.start()
        handler.post(runnableCode)
    }

    fun stopRecording(handler: Handler, runnableCode: Runnable) {
        recorder?.stop()
        recorder?.release()
        recorder = null
        handler.removeCallbacks(runnableCode)
    }

    fun getAmplitude(): Int {
        return if (recorder != null) {
            recorder?.maxAmplitude?:0
        } else {
            0
        }
    }

    fun getNormalizedAmplitude(amp: Int) : Int {
        if (amp< 0) {
            return 0
        } else if (amp > 2000) {
            return 100
        }
        return (((amp - 0.0) / 2000.0) * 100.0).toInt()
    }
}