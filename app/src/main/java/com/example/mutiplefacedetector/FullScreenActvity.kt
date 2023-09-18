package com.example.mutiplefacedetector

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mutiplefacedetector.databinding.ActivityFullScreenActvityBinding
import java.lang.reflect.Method


class FullScreenActvity : AppCompatActivity() {
    private val binding by lazy { ActivityFullScreenActvityBinding.inflate(layoutInflater) }

    /*private lateinit var cameraDetector: CameraDetector

    private val TAG = "MainActivity"
    private lateinit var notificationReceiver: BroadcastReceiver*/


    private lateinit var usbManager: UsbManager
    private lateinit var usbReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        usbManager = getSystemService(Context.USB_SERVICE) as UsbManager

        // Register a broadcast receiver to listen for USB device connection and disconnection events
        usbReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action
                if (UsbManager.ACTION_USB_DEVICE_ATTACHED == action) {
                    val device: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                    device?.let {
                        // A USB device is attached, do something with it
                        val deviceName = device.deviceName
                        val vendorId = device.vendorId
                        val productId = device.productId
                        // Display the device information
                        println("USB Device Attached:")
                        println("Device Name: $deviceName")
                        println("Vendor ID: $vendorId")
                        println("Product ID: $productId")
                    }
                } else if (UsbManager.ACTION_USB_DEVICE_DETACHED == action) {
                    val device: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                    device?.let {
                        // A USB device is detached, do something with it
                        val deviceName = device.deviceName
                        val vendorId = device.vendorId
                        val productId = device.productId
                        // Display the device information
                        println("USB Device Detached:")
                        println("Device Name: $deviceName")
                        println("Vendor ID: $vendorId")
                        println("Product ID: $productId")
                    }
                }
            }
        }

        // Register the broadcast receiver to listen for USB device events
        val filter = IntentFilter()
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        registerReceiver(usbReceiver, filter)



    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the broadcast receiver when the activity is destroyed
        unregisterReceiver(usbReceiver)
    }

    override fun onResume() {
        super.onResume()
      //  cameraDetector.detectCameras()
//        binding.textViewDevices.text = "Device : "+cameraDetector.detectCameras().size
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            Toast.makeText(this,"sorry you can't open notifications on quiz time ",Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"NOTIFICATION BAR IS DOWN",Toast.LENGTH_SHORT).show()
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
