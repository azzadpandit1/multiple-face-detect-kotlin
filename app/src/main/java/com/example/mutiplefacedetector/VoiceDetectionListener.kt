package com.example.mutiplefacedetector

interface VoiceDetectionListener {
    fun onVoiceDetected(amplitude: Double, isNiceDetected: Boolean, isRunning: Boolean)
   /* fun onVoiceToText(get: String?)*/
}