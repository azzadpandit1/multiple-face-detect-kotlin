package com.example.mutiplefacedetector

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.size
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.FaceDetector

class EyeTrackingActivity : AppCompatActivity() , SurfaceHolder.Callback, Detector.Processor<Face> {

    private lateinit var surfaceView: SurfaceView
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var cameraSource: CameraSource
    private lateinit var faceDetector: FaceDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eye_tracking)
        surfaceView = findViewById(R.id.surfaceView)
        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(this)

        // Check camera permission
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
    }

    private fun startCamera() {

        faceDetector = FaceDetector.Builder(this)
            .setTrackingEnabled(false)
            .setProminentFaceOnly(true)

            .build()

        if (!faceDetector.isOperational) {
            // Handle face detector initialization error
            return
        }

        cameraSource = CameraSource.Builder(this, faceDetector)
            .setRequestedPreviewSize(640, 480)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setAutoFocusEnabled(true)
            .setRequestedFps(30.0f)
            .build()


        faceDetector.setProcessor(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // Handle surface creation if needed
        try {
            if (ActivityCompat.checkSelfPermission(
                    this@EyeTrackingActivity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            cameraSource.start(surfaceHolder)
        } catch (e: Exception) {
            // Handle camera source start error
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Handle surface changes if needed
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // Handle surface destruction if needed
        cameraSource.stop()
    }

    override fun release() {
        // Release resources if needed
    }

    override fun receiveDetections(detections: Detector.Detections<Face>) {

        val detectedFaces: SparseArray<Face> = detections.detectedItems
        Log.e("TAG", "receiveDetections: -- "+detectedFaces.size() )

        if (detectedFaces.size==1){
            Log.d("TAG", "receiveDetections: one face"+detectedFaces.size())
        }

        runOnUiThread {
            Toast.makeText(this,"face size = > "+ detectedFaces.size(),Toast.LENGTH_SHORT).show()
        }

        if (detectedFaces.size() != 0) {
            /*runOnUiThread {
                Toast.makeText(this,"face size = > "+ detectedFaces.size(),Toast.LENGTH_SHORT).show()
            }*/
            // No faces detected
            // Handle the case when no faces are detected
        } else {
            // Faces detected
            // Handle the case when faces are detected
            /*runOnUiThread {
                Toast.makeText(this,"face size = > "+ detectedFaces.size(),Toast.LENGTH_SHORT).show()
            }*/
        }



       /* val faces = detections.detectedItems
        runOnUiThread {
            Toast.makeText(this,"face size = > "+ faces.size(),Toast.LENGTH_SHORT).show()
        }
        Log.e("TAG", "receiveDetections: "+faces.size() )

        if ((faces.size() ?: 0) > 0) {
            val canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.TRANSPARENT)

            val paint = Paint()
            paint.color = Color.GREEN
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 5f

            for (i in 0 until faces?.size()!!) {
                val face = faces.valueAt(i)
                val x1 = face.position.x
                val y1 = face.position.y
                val x2 = x1 + face.width
                val y2 = y1 + face.height

                canvas.drawCircle(
                    (x1 + x2) / 2,
                    (y1 + y2) / 2,
                    (x2 - x1) / 2,
                    paint
                )
            }

            surfaceHolder.unlockCanvasAndPost(canvas)
        }*/
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }
}