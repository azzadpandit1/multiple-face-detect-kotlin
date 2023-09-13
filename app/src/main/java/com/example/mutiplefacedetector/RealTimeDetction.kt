package com.example.mutiplefacedetector

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mutiplefacedetector.databinding.ActivityRealTimeDetctionBinding
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.face.FaceDetector
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import java.nio.ByteBuffer

class RealTimeDetction : AppCompatActivity(), SurfaceHolder.Callback, Camera.PreviewCallback {

    private val binding by lazy { ActivityRealTimeDetctionBinding.inflate(layoutInflater) }
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var camera: Camera

    private val CAMERA_PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT)
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

        val parameters = camera.parameters
        val width = parameters.previewSize.width
        val height = parameters.previewSize.height

        val frame = Frame.Builder()
            .setImageData(ByteBuffer.wrap(data), width, height, ImageFormat.NV21)
            .build()

        val detector = FaceDetector.Builder(this)
            .setTrackingEnabled(false)
//            .setClassificationType(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
            .setProminentFaceOnly(true)
            .setMode(FaceDetectorOptions.CONTOUR_MODE_NONE)
            .build()


     /*   val options = FaceDetectorOptions.Builder()
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_NONE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()*/

        val faces = detector.detect(frame)

        // Process the detected faces
        for (i in 0 until faces.size()) {
            val face = faces.valueAt(i)
            // Do something with the detected face
            // ...

            Log.e("TAG", "onPreviewFrame: "+faces.size() )

            runOnUiThread {
                binding.textView.text = faces.size().toString()
            }

        }

        detector.release()
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
                        binding.textView.text = "lable size" + detectedObjects.size
//                        Toast.makeText(this,"lable  -- > "+detectedObjects.size,Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occur during object detection
                binding.textView.text = ""
            }


    }

    // Create a function to detect faces in a bitmap image
    private fun faceDetectionInBitmap(bitmap: Bitmap) {
        // Create a FaceDetectorOptions object to configure the face detector
        val options = FaceDetectorOptions.Builder()
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_NONE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
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
                    // Process each face
                    // ...
                    runOnUiThread {
                        binding.textView.text = "Face size" + faces.size
//                        Toast.makeText(this,"total faces"+faces.size,Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
                // ...
                binding.textView.text = ""
            }

    }

}