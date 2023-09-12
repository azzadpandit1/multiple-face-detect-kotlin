package com.example.mutiplefacedetector

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.SparseArray
import android.view.PixelCopy
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.face.Contour.LEFT_EYE
import com.google.android.gms.vision.face.Contour.RIGHT_EYE
import com.google.android.gms.vision.face.Landmark.NOSE_BASE
import com.google.android.gms.vision.face.LargestFaceFocusingProcessor
import com.google.mlkit.vision.face.FaceLandmark.MOUTH_LEFT
import com.google.mlkit.vision.face.FaceLandmark.MOUTH_RIGHT
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.FaceDetector
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class EyeTrackingActivity : AppCompatActivity() , SurfaceHolder.Callback , Detector.Processor<Face> {

    private lateinit var camera: Camera
    private lateinit var surfaceHolder: SurfaceHolder

    private lateinit var faceDetector: FaceDetector
    private lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eye_tracking)
        // Request camera permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            startCamera()
        }

        // Keep the screen on
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Initialize SurfaceView and SurfaceHolder
        val surfaceView: SurfaceView = findViewById(R.id.surfaceView)
        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(this)
    }

    private fun startCamera() {
        camera = Camera.open()
        camera.setDisplayOrientation(90)

        // Check for camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // Start the CameraSource
            try {
                cameraSource.start(surfaceHolder)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Initialize the FaceDetector
        faceDetector = FaceDetector.Builder(this)
            .setTrackingEnabled(false)
            .build()

        // Set the processor for the FaceDetector
        faceDetector.setProcessor(this)

        // Initialize the CameraSource
        cameraSource = CameraSource.Builder(this, faceDetector)
            .setFacing(CameraSource.CAMERA_FACING_FRONT)
            .setRequestedFps(30.0f)
            .build()


    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (surfaceHolder.surface == null) {
            return
        }

        try {
            camera.stopPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            camera.setPreviewDisplay(surfaceHolder)
            camera.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        camera.stopPreview()
        camera.release()

        faceDetector.release()
        cameraSource.release()

    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }

    override fun release() {

    }

    override fun receiveDetections(detections: Detector.Detections<Face>) {
        // Get the detected faces
        val faces = detections.detectedItems
        Log.e("TAG", "receiveDetections: "+faces.size() )

        // Draw circles around the detected faces
        val canvas = surfaceHolder.lockCanvas()
        canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR)
        val paint = Paint()
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        for (i in 0 until faces.size()) {
            val face = faces.valueAt(i)
            val x = face.position.x
            val y = face.position.y
            val width = face.width
            val height = face.height

            canvas.drawCircle(x + width / 2, y + height / 2, width / 2, paint)
        }

        surfaceHolder.unlockCanvasAndPost(canvas)
    }
}