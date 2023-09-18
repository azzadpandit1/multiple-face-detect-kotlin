package com.example.mutiplefacedetector

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager

class StatusBarTouchListener(private val window: Window) : View.OnTouchListener {

    var int = 0
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val statusBarHeight = getStatusBarHeight()
            val touchY = event.rawY.toInt()

            if (touchY < statusBarHeight) {
                // Status bar was touched
                // Add your code here to handle the touch event
                int++
                Log.e("TAG", "onTouch: Status bar  clicks$int       --->+$statusBarHeight", )
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                return false // Stop touch event
            }
        }
      /*  when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Handle touch down event
                Log.e("Handle", "onTouch: Handle touch down event ˅", )
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // Handle touch move event
                Log.e("Handle", "onTouch: Handle touch move event ~~~~~~~", )
                return true
            }
            MotionEvent.ACTION_UP -> {
                // Handle touch up event
                Log.e("Handle", "onTouch: Handle touch up event  ˄", )
                return true
            }
        }*/
        return true
    }

    private fun getStatusBarHeight(): Int {
        val resourceId = window.context.resources.getIdentifier(
            "status_bar_height", "dimen", "android"
        )
        return if (resourceId > 0) {
            window.context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

}