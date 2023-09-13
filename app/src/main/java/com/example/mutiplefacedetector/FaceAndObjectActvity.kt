package com.example.mutiplefacedetector

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mutiplefacedetector.databinding.ActivityFaceAndObjectActvityBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import java.io.IOException


class FaceAndObjectActvity : AppCompatActivity(), SurfaceHolder.Callback, Camera.PictureCallback {
    private val binding by lazy { ActivityFaceAndObjectActvityBinding.inflate(layoutInflater) }

    var isDetectedObject = false

    //    private lateinit var surfaceView: SurfaceView
    private lateinit var surfaceHolder: SurfaceHolder

    private val requiredPermissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val requestCodePermissions = 101

    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        surfaceHolder = binding.surfaceView.holder
        surfaceHolder.addCallback(this)

        // Check and request necessary permissions
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, requiredPermissions, requestCodePermissions)
        }

        captureImage()

        findViewById<SurfaceView>(R.id.surfaceView).setOnClickListener {
            captureImage()
        }

    }

    private fun captureImage() {
        if (camera != null) {
            camera!!.setDisplayOrientation(90)
            camera!!.takePicture(null, null, this)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // Start the camera preview when the surface is created
        startCameraPreview()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // No implementation needed
        resetCamera()

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        releaseCamera()
    }

    private fun startCameraPreview() {

        camera = Camera.open()
        camera!!.setDisplayOrientation(90)
        try {
            camera!!.setPreviewDisplay(surfaceHolder)
            camera!!.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onPictureTaken(bytes: ByteArray?, camera: Camera?) {
        if (bytes != null) {
            saveImage(bytes)
        }
        resetCamera()

    }

    private fun releaseCamera() {
        camera!!.stopPreview()
        camera!!.release()
        camera = null
    }

    private fun resetCamera() {
        if (surfaceHolder!!.surface == null) {
            // Return if preview surface does not exist
            return
        }

        // Stop if preview surface is already running.
        camera!!.stopPreview()
        try {
            // Set preview display
            camera!!.setPreviewDisplay(surfaceHolder)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Start the camera preview...
        camera!!.startPreview()
    }


    private fun saveImage(bytes: ByteArray) {

        var bitmapOfimage = bytes.toBitmap()


        objectDetectionInBitmap(bitmapOfimage)

        faceDetectionInBitmap(bitmapOfimage)  // Done Code

        /* val outStream: FileOutputStream
         try {
             val fileName = "TUTORIALWING_" + System.currentTimeMillis() + ".jpg"
             val file = File(
                 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                 fileName
             )

             outStream = FileOutputStream(file)
             outStream.write(bytes)
             outStream.close()
             Toast.makeText(this@FaceAndObjectActvity, "Picture Saved: $fileName", Toast.LENGTH_LONG).show()
         } catch (e: FileNotFoundException) {
             e.printStackTrace()
         } catch (e: IOException) {
             e.printStackTrace()
         }*/
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


    fun ByteArray.toBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }

    override fun onResume() {
        super.onResume()
    }
}
