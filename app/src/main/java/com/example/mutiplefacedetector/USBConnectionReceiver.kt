package com.example.mutiplefacedetector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbManager
import android.util.Log
import android.widget.Toast

class USBConnectionReceiver : BroadcastReceiver() {
    val voiceDetectionListener : VoiceDetectionListener? = null
    init {
        Log.e("TAG", ": BroadcastReceiver is Created now ", )

    }
    override fun onReceive(context: Context, intent: Intent) {
        val connected = intent.getBooleanExtra("connected", false)
        val mtp = intent.getBooleanExtra("mtp", false)
        val hostConnected = intent.getBooleanExtra("host_connected", false)
        val adb = intent.getBooleanExtra("adb", false)
        val rndis = intent.getBooleanExtra("rndis", false)
        val usbFunctionPtp = intent.getBooleanExtra("USB_FUNCTION_PTP", false)
        val ptp = intent.getBooleanExtra("ptp", false)
        val audioSource = intent.getBooleanExtra("audio_source", false)
        val midi = intent.getBooleanExtra("midi", false)


        voiceDetectionListener?.onVoiceToText("onReceive: connected -> "+connected +" \n"
            +"onReceive: mtp -> "+mtp+" \n"
            +"onReceive: hostConnected -> "+hostConnected+" \n"
            +"onReceive: adb -> "+adb+" \n"
            +"onReceive: rndis -> "+rndis+" \n"
            +"onReceive: usbFunctionPtp -> "+usbFunctionPtp+" \n"
            +"onReceive: ptp -> "+ptp+" \n"
            +"onReceive: audioSource -> "+audioSource+" \n"
            +"onReceive: midi -> "+midi+" \n"
        )
        Log.e("TAG", "onReceive: connected -> "+connected )
        Log.e("TAG", "onReceive: mtp -> "+mtp )
        Log.e("TAG", "onReceive: hostConnected -> "+hostConnected )
        Log.e("TAG", "onReceive: adb -> "+adb )
        Log.e("TAG", "onReceive: rndis -> "+rndis )
        Log.e("TAG", "onReceive: usbFunctionPtp -> "+usbFunctionPtp )
        Log.e("TAG", "onReceive: ptp -> "+ptp )
        Log.e("TAG", "onReceive: audioSource -> "+audioSource )
        Log.e("TAG", "onReceive: midi -> "+midi )

    }
}