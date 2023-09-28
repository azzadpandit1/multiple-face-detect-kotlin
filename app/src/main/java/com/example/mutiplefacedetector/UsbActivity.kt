package com.example.mutiplefacedetector

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.hardware.usb.UsbManager
import android.media.FaceDetector
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mutiplefacedetector.databinding.ActivityUsbBinding
import com.google.android.datatransport.BuildConfig
import com.google.android.gms.vision.face.Landmark
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark
import com.google.mlkit.vision.face.FaceLandmark.NOSE_BASE
import java.io.FileOutputStream
import java.util.concurrent.ExecutorService
import kotlin.math.sqrt


class UsbActivity : AppCompatActivity() , VoiceDetectionListener{
    private val binding by lazy { ActivityUsbBinding.inflate(layoutInflater) }
    var context: Context? = null

    private val usbConnectionReceiver = USBConnectionReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Register the USB connection receiver
        val filter = IntentFilter()
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED)
        registerReceiver(usbConnectionReceiver, filter)

        isUsbConnected(this)

//        Toast.makeText(this,"Checkek")
    }
    // Create a function to check if the USB is connected
    private fun isUsbConnected(context: Context): Boolean {
        val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val deviceList = usbManager.deviceList
        return !deviceList.isEmpty()
    }

    override fun onResume() {
        super.onResume()

    }


    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the USB connection receiver
        unregisterReceiver(usbConnectionReceiver)
    }

    override fun onVoiceDetected(
        amplitude: Double,
        isNiceDetected: Boolean,
        isRunning: Boolean,
        typeOfVoiceDetected: String
    ) {

    }

    override fun onVoiceToText(get: String?) {
       binding.surfaceView.text = get
    }


}