package com.example.mutiplefacedetector

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.size
import com.example.mutiplefacedetector.databinding.ActivityCameraBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.FaceDetector
import com.google.android.gms.vision.face.FaceDetector.Builder
import java.io.IOException

class CameraActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCameraBinding.inflate(layoutInflater) }

    private lateinit var surfaceView: SurfaceView
    private lateinit var cameraSource: CameraSource
    private lateinit var faceDetector: FaceDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        surfaceView = findViewById(R.id.surfaceView)

        // Check for camera permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST
            )
        } else {
            initializeFaceDetector()
            startCameraSource()
        }
    }

    private fun initializeFaceDetector() {
        val context = applicationContext
        val gmsDetector = Builder(context)
            .setTrackingEnabled(false)
            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
            .build()

        faceDetector = gmsDetector.apply {
            setProcessor(object : Detector.Processor<Face> {
                override fun release() {}

                override fun receiveDetections(detections: Detector.Detections<Face>) {
                    val faces = detections.detectedItems
                    Log.e(TAG, "receiveDetections: size of face"+faces.size )
                    if (faces.size() > 0) {
                        drawFacesOnSurfaceView(faces)
                    }
                }
            })
        }
    }

    private fun startCameraSource() {
        val context = applicationContext
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)

        if (resultCode == ConnectionResult.SUCCESS) {
            cameraSource = CameraSource.Builder(context, faceDetector)
                .setRequestedPreviewSize(640, 480)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setAutoFocusEnabled(true)
                .setRequestedFps(30.0f)
                .build()

            surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return
                        }
                        cameraSource.start(surfaceView.holder)
                    } catch (e: IOException) {
                        Log.e(TAG, "Error starting camera source: ${e.message}")
                    }
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder,
                    format: Int,
                    width: Int,
                    height: Int
                ) {
                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    cameraSource.stop()
                }
            })
        }
    }

    private fun drawFacesOnSurfaceView(faces: SparseArray<Face>) {
        Log.e(TAG, "drawFacesOnSurfaceView: new output "+faces.size )
        val canvas = surfaceView.holder.lockCanvas()
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)

        val paint = Paint().apply {
            color = Color.GREEN
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }

        for (i in 0 until faces.size()) {
            val face = faces.valueAt(i)
            val x1 = face.position.x
            val y1 = face.position.y
            val x2 = x1 + face.width
            val y2 = y1 + face.height

            canvas.drawRect(x1, y1, x2, y2, paint)
        }

        surfaceView.holder.unlockCanvasAndPost(canvas)
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val CAMERA_PERMISSION_REQUEST = 1001
    }
}