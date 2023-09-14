package com.example.mutiplefacedetector

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.YuvImage
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.mutiplefacedetector.databinding.ActivityRealTimeDetctionBinding
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import java.io.ByteArrayOutputStream


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
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


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
        faceDetectionInBitmap(bitmap)
//        objectDetectionInBitmap(bitmap)





    }

    private fun objectDetectionInBitmap(bitmap: Bitmap) {
        // Create an object detector options instance
        val options = ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptionsBase.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()
            .enableClassification()
            .build()

        // Create an object detector using the options
        val objectDetector = ObjectDetection.getClient(options)
        // Create an input image from the bitmap
        val image = InputImage.fromBitmap(bitmap, binding.root.display.rotation)
        // Process the image and detect objects
        objectDetector.process(image)
            .addOnSuccessListener { detectedObjects ->
                // Process the detected objects
                for (detectedObject in detectedObjects) {
                    val boundingBox = detectedObject.boundingBox
                    val labels = detectedObject.labels
                    // Process the bounding box and labels
                    runOnUiThread {
                        binding.textViewObject.text = "lable size" + detectedObjects.size
//                        Toast.makeText(this,"lable  -- > "+detectedObjects.size,Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occur during object detection
                binding.textViewObject.text = ""
            }


    }

    // Create a function to detect faces in a bitmap image
    private fun faceDetectionInBitmap(bitmap: Bitmap) {
        // Create a FaceDetectorOptions object to configure the face detector
        val options = FaceDetectorOptions.Builder()
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_NONE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

        // Create a FaceDetector using the options
        val faceDetector = FaceDetection.getClient(options)

        // Create an InputImage object from the bitmap
        val inputImage = InputImage.fromBitmap(bitmap,  binding.root.display.rotation)

        // Process the image and detect faces
        faceDetector.process(inputImage)
            .addOnSuccessListener { faces ->
                // Handle the detected faces

                for (face in faces) {
                    this.runOnUiThread(Runnable {
                        if (faces.size <= 1){
                            binding.textViewFaceCount.text = "Face size" + faces.size
                            binding.textViewFaceCount.setBackgroundColor(Color.GREEN)
                        }else{
                            binding.textViewFaceCount.text = "Face size" + faces.size
                            binding.textViewFaceCount.setBackgroundColor(Color.RED)
                        }
                        val rectPaint = Paint().apply {
                        color = Color.RED
                        style = Paint.Style.STROKE
                        strokeWidth = 4f
                    }

                        try {
                            val rect = RectF(face.boundingBox)
                            if (surfaceHolder.surface.isValid) {
                                val canvas = surfaceHolder.lockCanvas()
                                if (canvas != null) {
                                    canvas.drawRect(rect, rectPaint)
                                    surfaceHolder.unlockCanvasAndPost(canvas)
                                } else {
                                    // Handle the case where the canvas is null
                                    Log.e("Canvas Error", "Canvas is null")
                                    // Add your error handling code here
                                }
                            } else {
                                // Handle the case where the surface view holder is not valid
                                Log.e("Surface Holder Error", "Surface holder is not valid")
                                // Add your error handling code here
                            }
                        }
                        catch (e: Exception) {
                            // Handle the exception here
                            Log.e("Crash", "An exception occurred: ${e.message}")
                            // Add your exception handling code here
                        }
                    })
                    /*activity.runOnUiThread(Runnable { //No.2
                        Toast.makeText(getActivity(), "This is correct way", Toast.LENGTH_SHORT)
                            .show()
                    })*/
                    // Process each face
                  /*  runOnUiThread {

                        if (faces.size <= 1){
                            binding.textViewFaceCount.text = "Face size" + faces.size
                            binding.textViewFaceCount.setBackgroundColor(Color.GREEN)
                        }else{
                            binding.textViewFaceCount.text = "Face size" + faces.size
                            binding.textViewFaceCount.setBackgroundColor(Color.RED)
                        }
                        val rectPaint = Paint().apply {
                            color = Color.RED
                            style = Paint.Style.STROKE
                            strokeWidth = 4f
                        }

                        *//*try {
                            val rect = RectF(face.boundingBox)
                            if (surfaceHolder.surface.isValid) {
                                val canvas = surfaceHolder.lockCanvas()
                                if (canvas != null) {
                                    canvas.drawRect(rect, rectPaint)
                                    surfaceHolder.unlockCanvasAndPost(canvas)
                                } else {
                                    // Handle the case where the canvas is null
                                    Log.e("Canvas Error", "Canvas is null")
                                    // Add your error handling code here
                                }
                            } else {
                                // Handle the case where the surface view holder is not valid
                                Log.e("Surface Holder Error", "Surface holder is not valid")
                                // Add your error handling code here
                            }
                        } catch (e: Exception) {
                            // Handle the exception here
                            Log.e("Crash", "An exception occurred: ${e.message}")
                            // Add your exception handling code here
                        }*//*


                        binding.progressHorizontal.isVisible = false

                    }*/
                }
            }
            .addOnFailureListener { exception ->
                binding.progressHorizontal.isVisible = false
                binding.textViewFaceCount.text = exception.stackTraceToString()
            }

    }

}