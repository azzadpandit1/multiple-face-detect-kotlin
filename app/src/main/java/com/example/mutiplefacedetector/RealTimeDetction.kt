package com.example.mutiplefacedetector

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.graphics.YuvImage
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.mutiplefacedetector.databinding.ActivityRealTimeDetctionBinding
import com.google.android.material.navigation.NavigationBarPresenter
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceContour
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark
import com.google.mlkit.vision.face.FaceLandmark.MOUTH_BOTTOM
import com.google.mlkit.vision.face.FaceLandmark.MOUTH_LEFT
import com.google.mlkit.vision.face.FaceLandmark.MOUTH_RIGHT
import com.google.mlkit.vision.face.FaceLandmark.NOSE_BASE
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.opencv.android.OpenCVLoader
import org.opencv.core.Core.sqrt
import java.io.ByteArrayOutputStream
import kotlin.math.pow
import kotlin.math.sqrt

// Import the necessary classes

private const val TAG = "FaceComparison"

class RealTimeDetction : AppCompatActivity(), SurfaceHolder.Callback, Camera.PreviewCallback {

    private val binding by lazy { ActivityRealTimeDetctionBinding.inflate(layoutInflater) }
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var camera: Camera
    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        context = this

        surfaceHolder = binding.surfaceView.holder
        surfaceHolder.addCallback(this)


        Log.e(TAG, "onCreate: open cv" + OpenCVLoader.OPENCV_VERSION)
        if (!OpenCVLoader.initDebug()) {
            binding.textViewObject.text = " Open CV faild"
        } else {
            binding.textViewObject.text = OpenCVLoader.OPENCV_VERSION.toString()
        }

    }


    override fun surfaceCreated(holder: SurfaceHolder) {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            surfaceHolder = holder
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT)
//            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK)
            camera.setPreviewDisplay(holder)
            camera.setDisplayOrientation(90)
            camera.setPreviewCallback(this)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        camera.startPreview()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        camera.stopPreview()
        camera.release()
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
        val lastUpdatedBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        binding.progressHorizontal.isVisible = true

        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .enableTracking()
            .build()

        val detector = FaceDetection.getClient(options)


        val options1 = ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptionsBase.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()
            .enableClassification().build()

        // Create an object detector using the options
        val objectDetector = ObjectDetection.getClient(options1)


        if (data != null) {
//            90 for Camera back
//            270 for Camera front
            val frame = InputImage.fromByteArray(
                data,
                camera.parameters.previewSize.width,
                camera.parameters.previewSize.height,
                270,
                InputImage.IMAGE_FORMAT_NV21
            )
            binding.ivStatus.isVisible = false

            CoroutineScope(Dispatchers.Default).launch {
                detector.process(frame).addOnSuccessListener { faces ->

                        if (faces.size == 0) {
                            binding.textViewFaceCount.text = "Face size " + faces.size
                            binding.textViewFaceCount.setBackgroundColor(Color.RED)
                            binding.progressHorizontal.isVisible = false

                            binding.ivStatus.setImageResource(R.drawable.outline_cancel_24)
                            binding.ivStatus.isVisible = true

                            return@addOnSuccessListener
                        }

                        // Process the detected faces
                        for (face in faces) {
                            // Access face landmarks, bounding box, etc.
                            runOnUiThread {
                                if (faces.size == 1) {
                                    binding.textViewFaceCount.text = "Face size " + faces.size
                                    binding.textViewFaceCount.setBackgroundColor(Color.GREEN)
                                    binding.progressHorizontal.isVisible = false

                                    binding.ivStatus.setImageResource(R.drawable.baseline_done_24)
                                    binding.ivStatus.isVisible = true

                                    val leftEyeOpenProbability = face.leftEyeOpenProbability
                                    val rightEyeOpenProbability = face.rightEyeOpenProbability

                                    // Check if the left eye is open
                                    if (leftEyeOpenProbability != null && leftEyeOpenProbability > 0.5) {
                                        // Perform desired actions
                                        showToast("left eye is open")
                                    }

                                    // Check if the right eye is open
                                    if (rightEyeOpenProbability != null && rightEyeOpenProbability > 0.5) {
                                        // Perform desired actions
                                        showToast("right eye is open")
                                    }

                                    // Check if both eyes are open
                                    if (leftEyeOpenProbability != null && rightEyeOpenProbability != null) {
                                        if (leftEyeOpenProbability > 0.5 && rightEyeOpenProbability > 0.5) {
                                            // Perform desired actions
                                            showToast("both eyes are open")
                                        }
                                    }

                                    // Check if both eyes are closed
                                    if (leftEyeOpenProbability != null && rightEyeOpenProbability != null) {
                                        if (leftEyeOpenProbability <= 0.2 && rightEyeOpenProbability <= 0.2) {
                                            // Perform desired actions
                                            showToast("both eyes are closed")
                                        }
                                    }


                                    if (checkLipMovement1(face)) {
                                        binding.ivStatus.setBackgroundResource(R.drawable.baseline_tag_faces_open)
                                    } else {
                                        binding.ivStatus.setBackgroundResource(R.drawable.baseline_face_close)
                                    }


                                } else {
                                    binding.textViewFaceCount.text = "Face size " + faces.size
                                    binding.textViewFaceCount.setBackgroundColor(Color.RED)
                                    binding.progressHorizontal.isVisible = false

                                    binding.ivStatus.setImageResource(R.drawable.outline_cancel_24)
                                    binding.ivStatus.isVisible = true

                                }
                            }
                        }
                    }.addOnFailureListener { e ->
                        // Handle any errors
                        runOnUiThread {
                            binding.textViewFaceCount.text = e.message

                        }
                    }

                objectDetector.process(frame).addOnSuccessListener { detectedObjects ->
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
                    }.addOnFailureListener { exception ->
                        // Handle any errors that occur during object detection
                        runOnUiThread {
                            binding.textViewObject.text = exception.message
                        }
                    }
            }

        }


    }

    private fun checkLipMovement1(face: Face?): Boolean {
        // Get the face bounds
        face?.let {
            val bounds = face.boundingBox

            // Get the mouth landmarks
            val mouthBottom = face.getLandmark(MOUTH_BOTTOM)
            val mouthLeft = face.getLandmark(MOUTH_LEFT)
            val mouthRight = face.getLandmark(MOUTH_RIGHT)

            mouthLeft?.let { left ->
                mouthRight?.let { right ->
                    mouthBottom?.let { bottom ->
                        /*// Calculate the distance between the mouth landmarks
                        val mouthWidth = right.position.x - left.position.x
                        val mouthHeight = bottom.position.y - left.position.y

                        // Determine the open or close status based on the mouth width and height
                        val isOpen = mouthWidth > 0.2 * bounds.width() && mouthHeight > 0.1 * bounds.height()

                        // Check if the mouth is open
                        if (isOpen) {
                            Log.e(TAG, "checkLipMovement1: open")
                            println("Mouth is open")
                        } else {
                            // Check if the mouth is closed
                            val isClosed = mouthWidth < 0.1 * bounds.width() && mouthHeight < 0.05 * bounds.height()
                            if (isClosed) {
                                Log.e(TAG, "checkLipMovement1: close")
                                println("Mouth is closed")
                            } else {
                                Log.e(TAG, "checkLipMovement1: uncertain")
                                println("Mouth status is uncertain")
                            }
                        }*/
                        // Calculate the distance between the mouth landmarks
                        val mouthWidth = right.position.x - left.position.x
                        val mouthHeight = bottom.position.y - left.position.y

                        // Determine the open or close status based on the mouth width and height
                        val isOpen = mouthWidth > 0.2 * bounds.width() && mouthHeight > 0.1 * bounds.height()

                        // Print the result
                        if (isOpen) {
                            Log.e(TAG, "checkLipMovement1: open")
                            println("Mouth is open")
                        } else {
                            Log.e(TAG, "checkLipMovement1: close")
                            println("Mouth is closed")
                        }
                    }
                }
            }


        }

        // Get the face landmarks
       /* face?.let {
            val landmarks = face.allLandmarks

            // Get the nose base landmark
            val noseBase = landmarks[NOSE_BASE]

            // Get the left and right mouth landmarks
            val mouthLeft = landmarks[MOUTH_LEFT]
            val mouthRight = landmarks[MOUTH_RIGHT]

            *//*try {

                // Calculate the distance between the nose base and mouth landmarks
                val distance = calculateDistance(noseBase, mouthLeft, mouthRight)
                // Determine the open or close status of the mouth
                val threshold = 0.2
                val mouthStatus = if (distance > threshold) { "Open"
                    return true
                } else {
                    "Closed"
                    return false
                }
                // Print the mouth status
                println("Mouth status: $mouthStatus")

            }catch (e:Exception){
                Log.e(TAG, "checkLipMovement1: "+e.message )
            }*//*



        }*/
        return false
    }

    private fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }


    private fun matchFace(bitmap1: Bitmap, bitmap2: Bitmap) {
        // Create a FaceDetectorOptions object with the desired settings
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE).build()

        // Create a FaceDetector instance
        val faceDetector = FaceDetection.getClient(options)
        // Create two InputImage objects from the provided images
        val image1 = InputImage.fromBitmap(bitmap1, 90)
        val image2 = InputImage.fromBitmap(bitmap2, 90)

        // Process the first image and get the faces detected
        val result1 = faceDetector.process(image1).addOnSuccessListener { faces ->
                // Process the second image and get the faces detected
                val result2 = faceDetector.process(image2).addOnSuccessListener { faces2 ->
                        // Compare the faces detected in both images
                        val areFacesEqual = compareFaces(faces, faces2)
                        // Print the result
                        if (areFacesEqual) {
                            runOnUiThread {
                                Toast.makeText(
                                    this, "The faces are equal $areFacesEqual", Toast.LENGTH_SHORT
                                ).show()
                            }
                            println("The faces are equal.")
                        } else {
                            runOnUiThread {
                                Toast.makeText(
                                    this,
                                    "The faces are not equal $areFacesEqual",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
//                            println("The faces are not equal.")
                        }
                    }.addOnFailureListener { exception ->
                        // Handle any errors that occur during face detection
                        println("Face detection failed: ${exception.message}")
                    }
            }.addOnFailureListener { exception ->
                // Handle any errors that occur during face detection
                println("Face detection failed: ${exception.message}")
            }
    }

    // Function to compare the faces detected in both images
    private fun compareFaces(faces1: List<Face>, faces2: List<Face>): Boolean {
        if (faces1.size != faces2.size) {
            return false
        }

        for (i in faces1.indices) {
            val face1 = faces1[i]
            val face2 = faces2[i]

            // Compare bounding boxes
            if (face1.boundingBox != face2.boundingBox) {
                return false
            }

            // Compare landmarks
            if (face1.allLandmarks != face2.allLandmarks) {
                return false
            }
        }

        return true
    }

    override fun onPause() {
        super.onPause()/* if (camera != null) {
             camera.release()
         }*/
    }

    override fun onResume() {
        super.onResume()
//        disableStatusBarPullDown()

    }

    private fun checkLipMovement(face: Face): Boolean {
        val bounds = face.boundingBox
        val leftEye = face.getLandmark(FaceLandmark.LEFT_EYE)?.position
        val rightEye = face.getLandmark(FaceLandmark.RIGHT_EYE)?.position
        val smileProb = face.smilingProbability
        val leftEyeOpenProb = face.leftEyeOpenProbability
        val rightEyeOpenProb = face.rightEyeOpenProbability

        // Determine the mouth open and close status
        val mouthOpenCloseStatus = if (smileProb != null && smileProb > 0.4) {
            return true
            "Open"
        } else {
            "Closed"
            return false
        }
    }

}