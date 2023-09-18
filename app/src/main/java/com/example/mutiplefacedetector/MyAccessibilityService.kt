package com.example.mutiplefacedetector

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            // Handle the notification state change here
            // This code will be executed whenever a notification is added or removed
            Log.e("TAG", "onAccessibilityEvent: status "+event.eventType.toString() )

            // Collapse the notification panel
            val collapseIntent = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            collapseIntent.putExtra("command", "collapsePanels")
            sendBroadcast(collapseIntent)

        }
    }

    override fun onInterrupt() {
        // Handle service interruption here
    }
}