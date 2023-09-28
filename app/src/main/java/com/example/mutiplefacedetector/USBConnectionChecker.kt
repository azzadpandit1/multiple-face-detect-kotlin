package com.example.mutiplefacedetector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
interface USBConnectionListener {
    fun onUSBDeviceAttached()
    fun onUSBDeviceDetached()
}

class USBConnectionChecker(private val context: Context, private val listener: USBConnectionListener) {

    private val usbReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED == action) {
                // USB device is attached
                listener.onUSBDeviceAttached()
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED == action) {
                // USB device is detached
                listener.onUSBDeviceDetached()
            }
        }
    }

    fun startChecking() {
        val filter = IntentFilter()
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        context.registerReceiver(usbReceiver, filter)
    }

    fun stopChecking() {
        context.unregisterReceiver(usbReceiver)
    }
}