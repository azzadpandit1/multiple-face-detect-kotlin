package com.example.mutiplefacedetector

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.FaceDetector
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark
import com.google.mlkit.vision.face.FaceLandmark.*
import com.google.mlkit.vision.face.FaceLandmark.NOSE_BASE
import com.google.mlkit.vision.face.FaceLandmark.RIGHT_EAR
import com.google.mlkit.vision.face.FaceLandmark.RIGHT_EYE
import com.google.mlkit.vision.face.FaceLandmark.LEFT_EAR
import com.google.mlkit.vision.face.FaceLandmark.LEFT_EYE
import com.google.mlkit.vision.face.FaceLandmark.RIGHT_CHEEK
import com.google.mlkit.vision.face.FaceLandmark.LEFT_CHEEK
import java.io.IOException
import java.util.concurrent.Executors

class RealTimeActivity : AppCompatActivity() {

    private lateinit var cameraView: SurfaceView
    private lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_time)
        cameraView = findViewById(R.id.cameraView)

        val faceDetector = createFaceDetector()
        if (!faceDetector.isOperational) {
            Log.w(TAG, "Face detector dependencies are not yet available.")
        }

        cameraSource = CameraSource.Builder(this, faceDetector)
            .setRequestedPreviewSize(640, 480)
            .setFacing(CameraSource.CAMERA_FACING_FRONT)
            .setAutoFocusEnabled(true)
            .setRequestedFps(30.0f)
            .build()

        cameraView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if (ContextCompat.checkSelfPermission(
                        this@RealTimeActivity,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    try {
                        cameraSource.start(cameraView.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    ActivityCompat.requestPermissions(
                        this@RealTimeActivity,
                        arrayOf(Manifest.permission.CAMERA),
                        CAMERA_PERMISSION_REQUEST
                    )
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })
    }
    private fun createFaceDetector(): FaceDetector {
        val faceDetectorBuilder = FaceDetector.Builder(this)
            .setTrackingEnabled(false)
            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
            .setMode(FaceDetector.FAST_MODE)
            .build()

        faceDetectorBuilder.setProcessor(object : Detector.Processor<Face> {
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<Face>) {
                // Perform proctoring tasks using face detections
                val faces = detections.detectedItems
                // TODO: Implement your proctoring logic here
                Toast.makeText(this@RealTimeActivity, "total face -- > " + faces.size(), Toast.LENGTH_SHORT).show()

            }
        })

        return faceDetectorBuilder

    }

    companion object {
        private const val TAG = "MainActivity"
        private const val CAMERA_PERMISSION_REQUEST = 100
    }
}