package com.langiappeworkshop.autosafedemo

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.telephony.SmsManager
import android.widget.Toast


class SendSMS internal constructor(private val activity: Activity) {
    fun sendSMS(phoneNo: String, msg: String) {
        if (ActivityCompat.checkSelfPermission(
                this.activity,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                val smsMgrVar = SmsManager.getDefault()
                smsMgrVar.sendTextMessage(phoneNo, null, msg, null, null)
                Toast.makeText(
                    this.activity, "Message Sent",
                    Toast.LENGTH_LONG
                ).show()
            } catch (ErrVar: Exception) {
                Toast.makeText(
                    this.activity, ErrVar.message,
                    Toast.LENGTH_LONG
                ).show()
                ErrVar.printStackTrace()
            }

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(this.activity, arrayOf(Manifest.permission.SEND_SMS), 10)
            }
        }
    }

}
