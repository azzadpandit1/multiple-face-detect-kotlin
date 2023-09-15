package com.example.mutiplefacedetector

import android.app.Application
import android.os.StrictMode

class MyApplication : Application()  {
    override fun onCreate() {
        super.onCreate()
        enableStrictMode()
    }

    private fun enableStrictMode() {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }
}