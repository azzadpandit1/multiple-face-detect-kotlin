package com.example.mutiplefacedetector

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream

class NoiseDetector {
    private val SAMPLE_RATE = 44100
    private val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
    private val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
    private val BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)

    private var audioRecord: AudioRecord? = null
    private var isRunning = false
    fun start(context: Context , voiceDetectionListener : VoiceDetectionListener) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ) {
            return
        }

        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            SAMPLE_RATE,
            CHANNEL_CONFIG,
            AUDIO_FORMAT,
            BUFFER_SIZE
        )

        audioRecord?.startRecording()
        isRunning = true

        val buffer = ShortArray(BUFFER_SIZE)
        var sumAmplitude = 0L

        while (isRunning) {
            val readSize = audioRecord?.read(buffer, 0, BUFFER_SIZE)
            if (readSize != AudioRecord.ERROR_INVALID_OPERATION) {
                val amplitude = calculateAmplitude(buffer, readSize!!)

                for (i in 0 until readSize) {
                    sumAmplitude += Math.abs(buffer[i].toLong())
                }

                val averageAmplitude = sumAmplitude / readSize
                // Adjust these threshold values based on your requirements
                val humanVoiceThreshold = 5000
                val nonHumanVoiceThreshold = 2000
                var typeOfVoiceDetected :String=""
                if (averageAmplitude > humanVoiceThreshold){
                    typeOfVoiceDetected = "Human Voice Detected"
                    Log.e("detection", " Human Voice Detected ", )
                }else if (averageAmplitude <= nonHumanVoiceThreshold){
                    typeOfVoiceDetected = "Non-Human Voice Detected"
                    Log.e("detection", "Non-Human Voice Detected", )
                }else {
                    typeOfVoiceDetected = "Unknown Voice Detected"
                    Log.e("detection", " Unknown Voice Detected ", )
                }

                Log.e("TAG", "start: $amplitude")
                if (amplitude > 350) {
                    voiceDetectionListener.onVoiceDetected(amplitude,true,isRunning,typeOfVoiceDetected)
                }else{
                    voiceDetectionListener.onVoiceDetected(amplitude,false,isRunning,typeOfVoiceDetected)
                }
            }
        }

        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }

    fun stop() {
        isRunning = false
    }

    private fun calculateAmplitude(buffer: ShortArray, readSize: Int): Double {
        var sum = 0.0
        for (i in 0 until readSize) {
            sum += buffer[i].toDouble() * buffer[i].toDouble()
        }
        val amplitude = Math.sqrt(sum / readSize)
        return amplitude
    }

    // Function to save voice in local storage
    fun saveVoiceInLocalStorage(context: Context, voiceData: ByteArray, fileName: String) {
        val file = File(context.filesDir, fileName)
        val outputStream = FileOutputStream(file)
        outputStream.write(voiceData)
        outputStream.close()
    }
}

