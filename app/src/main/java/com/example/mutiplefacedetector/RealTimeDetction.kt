//package com.example.mutiplefacedetector
//
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.Color
//import android.graphics.Rect
//import android.graphics.YuvImage
//import android.hardware.Camera
//import android.os.Bundle
//import android.view.MotionEvent
//import android.view.SurfaceHolder
//import android.view.View
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.view.isVisible
//import com.example.mutiplefacedetector.databinding.ActivityRealTimeDetctionBinding
//import com.google.android.material.navigation.NavigationBarPresenter
//import com.google.mlkit.vision.common.InputImage
//import com.google.mlkit.vision.face.Face
//import com.google.mlkit.vision.face.FaceContour
//import com.google.mlkit.vision.face.FaceDetection
//import com.google.mlkit.vision.face.FaceDetectorOptions
//import com.google.mlkit.vision.face.FaceLandmark
//import com.google.mlkit.vision.objects.ObjectDetection
//import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
//import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.io.ByteArrayOutputStream
//
//// Import the necessary classes
//
//private const val TAG = "FaceComparison"
//
//class RealTimeDetction : AppCompatActivity(), SurfaceHolder.Callback, Camera.PreviewCallback {
//
//    private val binding by lazy { ActivityRealTimeDetctionBinding.inflate(layoutInflater) }
//    private lateinit var surfaceHolder: SurfaceHolder
//    private lateinit var camera: Camera
//    private val CAMERA_PERMISSION_REQUEST_CODE = 100
//    var context :Context? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//
//
//// Usage example
//        val window = window // Get the window object
//        val statusBarTouchListener = StatusBarTouchListener(window)
//        window.decorView.setOnTouchListener(statusBarTouchListener)
//
//        context = this
//
//        surfaceHolder = binding.surfaceView.holder
//        surfaceHolder.addCallback(this)
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.CAMERA),
//                CAMERA_PERMISSION_REQUEST_CODE
//            )
//        }
//
//
//    }
//
//
//    override fun surfaceCreated(holder: SurfaceHolder) {
//        surfaceHolder = holder
////        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT)
//        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK)
//        camera.setPreviewDisplay(holder)
//        camera.setDisplayOrientation(90)
//        camera.setPreviewCallback(this)
//
//    }
//
//    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
//        camera.startPreview()
//    }
//
//    override fun surfaceDestroyed(holder: SurfaceHolder) {
//        camera.stopPreview()
//        camera.release()
//    }
//
//    override fun onPreviewFrame(data: ByteArray?, camera: Camera) {
//        // Fixing the NullPointerException
//        if (data == null) {
//            return
//        }
//
//        // Convert the data to a bitmap
//        val parameters = camera.parameters
//        val width = parameters.previewSize.width
//        val height = parameters.previewSize.height
//        val yuvImage = YuvImage(data, parameters.previewFormat, width, height, null)
//        val out = ByteArrayOutputStream()
//        yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
//        val imageBytes = out.toByteArray()
//        val lastUpdatedBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//
//        binding.progressHorizontal.isVisible = true
//
//        val options = FaceDetectorOptions.Builder()
//            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
//            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
//            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
//            .enableTracking()
//            .build()
//
//        val detector = FaceDetection.getClient(options)
//
//
//        val options1 = ObjectDetectorOptions.Builder()
//            .setDetectorMode(ObjectDetectorOptionsBase.SINGLE_IMAGE_MODE)
//            .enableMultipleObjects()
//            .enableClassification()
//            .build()
//
//        // Create an object detector using the options
//        val objectDetector = ObjectDetection.getClient(options1)
//
//
//        if (data!=null){
//            val frame = InputImage.fromByteArray(
//                data,
//                camera.parameters.previewSize.width,
//                camera.parameters.previewSize.height,
//                90,
//                InputImage.IMAGE_FORMAT_NV21
//            )
//            binding.ivStatus.isVisible = false
//
//            CoroutineScope(Dispatchers.Default).launch {
//                detector.process(frame)
//                    .addOnSuccessListener { faces ->
//
//                        if (faces.size==0){
//                            binding.textViewFaceCount.text = "Face size " + faces.size
//                            binding.textViewFaceCount.setBackgroundColor(Color.RED)
//                            binding.progressHorizontal.isVisible = false
//
//                            binding.ivStatus.setImageResource(R.drawable.outline_cancel_24)
//                            binding.ivStatus.isVisible = true
//
//                            return@addOnSuccessListener
//                        }
//
//                        // Process the detected faces
//                        for (face in faces) {
//                            // Access face landmarks, bounding box, etc.
//                            runOnUiThread {
//                                if (faces.size == 1) {
//                                    binding.textViewFaceCount.text = "Face size " + faces.size
//                                    binding.textViewFaceCount.setBackgroundColor(Color.GREEN)
//                                    binding.progressHorizontal.isVisible = false
//
//                                    binding.ivStatus.setImageResource(R.drawable.baseline_done_24)
//                                    binding.ivStatus.isVisible = true
//
//                                    val leftEyeOpenProbability = face.leftEyeOpenProbability
//                                    val rightEyeOpenProbability = face.rightEyeOpenProbability
//
//                                    // Check if the left eye is open
//                                    if (leftEyeOpenProbability != null && leftEyeOpenProbability > 0.5) {
//                                        // Perform desired actions
//                                        showToast("left eye is open")
//                                    }
//
//                                    // Check if the right eye is open
//                                    if (rightEyeOpenProbability != null && rightEyeOpenProbability > 0.5) {
//                                        // Perform desired actions
//                                        showToast("right eye is open")
//                                    }
//
//                                    // Check if both eyes are open
//                                    if (leftEyeOpenProbability != null && rightEyeOpenProbability != null) {
//                                        if (leftEyeOpenProbability > 0.5 && rightEyeOpenProbability > 0.5) {
//                                            // Perform desired actions
//                                            showToast("both eyes are open")
//                                        }
//                                    }
//
//                                    // Check if both eyes are closed
//                                    if (leftEyeOpenProbability != null && rightEyeOpenProbability != null) {
//                                        if (leftEyeOpenProbability <= 0.5 && rightEyeOpenProbability <= 0.5) {
//                                            // Perform desired actions
//                                            showToast("both eyes are closed")
//                                        }
//                                    }
//
//                                   /* // Check if lip is moving
//                                    val lipIsMoving = isLipMoving(face)
//
//                                    // Perform actions based on lip movement
//                                    if (lipIsMoving) {
//                                        // Lip is moving
//                                        // Do something
//                                    } else {
//                                        // Lip is not moving
//                                        // Do something else
//                                    }*/
//
//                                }
//                                else {
//                                    binding.textViewFaceCount.text = "Face size " + faces.size
//                                    binding.textViewFaceCount.setBackgroundColor(Color.RED)
//                                    binding.progressHorizontal.isVisible = false
//
//                                    binding.ivStatus.setImageResource(R.drawable.outline_cancel_24)
//                                    binding.ivStatus.isVisible = true
//
//                                }
//                            }
//                        }
//                    }
//                    .addOnFailureListener { e ->
//                        // Handle any errors
//                        runOnUiThread {
//                            binding.textViewFaceCount.text = e.message
//
//                        }
//                    }
//
//                objectDetector.process(frame)
//                    .addOnSuccessListener { detectedObjects ->
//                        // Process the detected objects
//                        // Print all object names
//                        for (detectedObject in detectedObjects) {
//                            runOnUiThread {
//                                val labels = detectedObject.labels
//                                for (label in labels) {
//                                    binding.textViewObject.text = label.text
////                            println(label.text)
//                                }
//                            }
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        // Handle any errors that occur during object detection
//                        runOnUiThread {
//                            binding.textViewObject.text = exception.message
//                        }
//                    }
//            }
//
//        }
//
//
//    }
//
//   /* private fun isLipMoving(face: Face?): Boolean {
//        // Get lip landmarks
//        val lipBottom = face?.getLandmark(FaceLandmark.MOUTH_BOTTOM)?.position
//        val lipTop = face?.getLandmark(FaceLandmark.TOP_MOUTH)?.position
//
//        // Calculate lip height
//        val lipHeight = lipTop?.y?.minus(lipBottom?.y ?: 0f)
//
//        // Check if lip height is above a certain threshold
//        return lipHeight ?: 0f > 10f
//    }*/
//
//    private fun showToast(msg: String) {
//        runOnUiThread {
//            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    private fun matchFace(bitmap1:Bitmap,bitmap2:Bitmap){
//        // Create a FaceDetectorOptions object with the desired settings
//        val options = FaceDetectorOptions.Builder()
//            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
//            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
//            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
//            .build()
//
//// Create a FaceDetector instance
//        val faceDetector = FaceDetection.getClient(options)
//        // Create two InputImage objects from the provided images
//        val image1 = InputImage.fromBitmap(bitmap1, 90)
//        val image2 = InputImage.fromBitmap(bitmap2, 90)
//
//// Process the first image and get the faces detected
//        val result1 = faceDetector.process(image1)
//            .addOnSuccessListener { faces ->
//                // Process the second image and get the faces detected
//                val result2 = faceDetector.process(image2)
//                    .addOnSuccessListener { faces2 ->
//                        // Compare the faces detected in both images
//                        val areFacesEqual = compareFaces(faces, faces2)
//                        // Print the result
//                        if (areFacesEqual) {
//                            runOnUiThread {
//                            Toast.makeText(this, "The faces are equal $areFacesEqual",Toast.LENGTH_SHORT).show()
//                            }
//                            println("The faces are equal.")
//                        } else {
//                            runOnUiThread {
//                                Toast.makeText(this, "The faces are not equal $areFacesEqual",Toast.LENGTH_SHORT).show()
//                            }
////                            println("The faces are not equal.")
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        // Handle any errors that occur during face detection
//                        println("Face detection failed: ${exception.message}")
//                    }
//            }
//            .addOnFailureListener { exception ->
//                // Handle any errors that occur during face detection
//                println("Face detection failed: ${exception.message}")
//            }
//    }
//    // Function to compare the faces detected in both images
//    private fun compareFaces(faces1: List<Face>, faces2: List<Face>): Boolean {
//        if (faces1.size != faces2.size) {
//            return false
//        }
//
//        for (i in faces1.indices) {
//            val face1 = faces1[i]
//            val face2 = faces2[i]
//
//            // Compare bounding boxes
//            if (face1.boundingBox != face2.boundingBox) {
//                return false
//            }
//
//            // Compare landmarks
//            if (face1.allLandmarks != face2.allLandmarks) {
//                return false
//            }
//        }
//
//        return true
//    }
//
//    override fun onPause() {
//        super.onPause()
//       /* if (camera != null) {
//            camera.release()
//        }*/
//    }
//
//    override fun onResume() {
//        super.onResume()
////        disableStatusBarPullDown()
//
//    }
//   /* private fun disableStatusBarPullDown() {
//        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//
//        window.decorView.systemUiVisibility = flags
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//    }*/
//
//
//    // This snippet hides the system bars.
//    private fun hideSystemUI() {
//        // Set the IMMERSIVE flag.
//        // Set the content to appear under the system bars so that the content
//        // doesn't resize when the system bars hide and show.
//        window.decorView.setSystemUiVisibility(
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE
//        )
//    }
//
//    // This snippet shows the system bars. It does this by removing all the flags
//    // except for the ones that make the content appear under the system bars.
//    private fun showSystemUI() {
//        window.decorView.setSystemUiVisibility(
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        )
//    }
//
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus) {
//            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//        }
//    }
//}