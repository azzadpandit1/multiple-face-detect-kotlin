package com.example.mutiplefacedetector

interface VoiceDetectionListener {
    fun onVoiceDetected(
        amplitude: Double,
        isNiceDetected: Boolean,
        isRunning: Boolean,
        typeOfVoiceDetected: String
    )
    fun onVoiceToText(get: String?)
}