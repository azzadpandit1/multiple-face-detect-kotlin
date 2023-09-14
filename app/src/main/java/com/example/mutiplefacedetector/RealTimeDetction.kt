package com.example.mutiplefacedetector

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.mutiplefacedetector.databinding.ActivityRealTimeDetctionBinding
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.MultiDetector
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.Arrays


class RealTimeDetction : AppCompatActivity(), SurfaceHolder.Callback, Camera.PreviewCallback {

    private val binding by lazy { ActivityRealTimeDetctionBinding.inflate(layoutInflater) }
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var camera: Camera
    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    var context :Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        context = this


        surfaceHolder = binding.surfaceView.holder
        surfaceHolder.addCallback(this)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }


    }

    override fun surfaceCreated(holder: SurfaceHolder) {
//        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT)
        surfaceHolder = holder
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK)
        camera.setPreviewDisplay(holder)
        camera.setDisplayOrientation(90)
        camera.setPreviewCallback(this)

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        camera.startPreview()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        camera.stopPreview()
        camera.release()
    }

    /*fun ByteArray.toBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }
*/
    override fun onPreviewFrame(data: ByteArray?, camera: Camera) {
        // Fixing the NullPointerException
        if (data == null) {
            return
        }

        // Convert the data to a bitmap
        val parameters = camera.parameters
        val width = parameters.previewSize.width
        val height = parameters.previewSize.height
        val yuvImage = YuvImage(data, parameters.previewFormat, width, height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
        val imageBytes = out.toByteArray()
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)


        binding.progressHorizontal.isVisible = true

        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .build()

        val detector = FaceDetection.getClient(options)


        val options1 = ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptionsBase.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()
            .enableClassification()
            .build()

        // Create an object detector using the options
        val objectDetector = ObjectDetection.getClient(options1)


        if (data!=null){
            val frame = InputImage.fromByteArray(
                data,
                camera.parameters.previewSize.width,
                camera.parameters.previewSize.height,
                90,
                InputImage.IMAGE_FORMAT_NV21
            )
            detector.process(frame)
                .addOnSuccessListener { faces ->
                    // Process the detected faces
                    for (face in faces) {
                        // Access face landmarks, bounding box, etc.
                        runOnUiThread {
                            if (faces.size <= 1) {
                                binding.textViewFaceCount.text = "Face size " + faces.size  +" Display Angel " + binding.root.display.rotation
                                binding.textViewFaceCount.setBackgroundColor(Color.GREEN)
                                binding.progressHorizontal.isVisible = false
                            } else {
                                binding.textViewFaceCount.text = "Face size " + faces.size  +" Display Angel " + binding.root.display.rotation
                                binding.textViewFaceCount.setBackgroundColor(Color.RED)
                                binding.progressHorizontal.isVisible = false
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    // Handle any errors
                }

            objectDetector.process(frame)
                .addOnSuccessListener { detectedObjects ->
                    // Process the detected objects
                    // Print all object names
                    for (detectedObject in detectedObjects) {
                        runOnUiThread {
                            val labels = detectedObject.labels
                            for (label in labels) {
                                binding.textViewObject.text = label.text
//                            println(label.text)
                            }
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle any errors that occur during object detection
                    binding.textViewObject.text = ""
                }
        }
    }


}