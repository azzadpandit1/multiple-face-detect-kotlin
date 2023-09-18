package com.example.mutiplefacedetector

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mutiplefacedetector.databinding.ActivityFullScreenActvityBinding
import java.lang.reflect.Method


class FullScreenActvity : AppCompatActivity() {
    private val binding by lazy { ActivityFullScreenActvityBinding.inflate(layoutInflater) }

    private lateinit var cameraDetector: CameraDetector

    private lateinit var usbManager: UsbManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       /* val usbManager = getSystemService(USB_SERVICE) as UsbManager
        cameraDetector = CameraDetector(usbManager)*/

        usbManager = getSystemService(Context.USB_SERVICE) as UsbManager

        val usbCameraList: List<UsbDevice> = getConnectedUsbCameras()

        binding.textViewDevices.setOnClickListener {

            getConnectedUsbCameras()

            binding.textViewDevices.text = "Device : "+usbCameraList.size

        }


    }
    private fun getConnectedUsbCameras(): List<UsbDevice> {
        val usbCameraList: MutableList<UsbDevice> = mutableListOf()

        val usbDevices: HashMap<String, UsbDevice> = usbManager.deviceList

        for (device in usbDevices.values) {
            if (device.deviceClass == UsbConstants.USB_CLASS_VIDEO) {
                usbCameraList.add(device)
            }
        }

        return usbCameraList
    }
    override fun onDestroy() {
        super.onDestroy()

    }
    override fun onResume() {
        super.onResume()
        //cameraDetector.detectCameras()
        //binding.textViewDevices.text = "Device : "+cameraDetector.detectCameras().size
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            Toast.makeText(this,"sorry you can't open notifications on quiz time ",Toast.LENGTH_SHORT).show()
            // NOTIFICATION BAR IS DOWN...DO STUFF
            setExpandNotificationDrawer(this,false) // close notification panel
        }
    }

    @SuppressLint("WrongConstant", "PrivateApi")
    fun setExpandNotificationDrawer(context: Context, expand: Boolean) {
        try {
            val statusBarService = context.getSystemService("statusbar")
            val methodName =
                if (expand)
                    if (Build.VERSION.SDK_INT >= 17) "expandNotificationsPanel" else "expand"
                else
                    if (Build.VERSION.SDK_INT >= 17) "collapsePanels" else "collapse"
            val statusBarManager: Class<*> = Class.forName("android.app.StatusBarManager")
            val method: Method = statusBarManager.getMethod(methodName)
            method.invoke(statusBarService)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    class CameraDetector(private val usbManager: UsbManager) {

        fun detectCameras(): List<UsbDevice> {
            val cameraDevices = mutableListOf<UsbDevice>()

            val usbDevices = usbManager.deviceList
            for (device in usbDevices.values) {
                if (isCamera(device)) {
                    cameraDevices.add(device)
                }
            }

            return cameraDevices
        }

        private fun isCamera(device: UsbDevice): Boolean {
            val deviceClass = device.deviceClass
            val deviceSubclass = device.deviceSubclass

            return deviceClass == UsbConstants.USB_CLASS_VIDEO/* && deviceSubclass == UsbConstants.USB_SUBCLASS_VENDOR_SPEC*/
        }
    }

}
