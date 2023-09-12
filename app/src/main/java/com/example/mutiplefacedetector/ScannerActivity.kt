package com.example.mutiplefacedetector

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.mutiplefacedetector.databinding.ActivityScannerBinding
import com.example.mutiplefacedetector.viewModel.CameraXViewModel
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class ScannerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityScannerBinding.inflate(layoutInflater) }
    private lateinit var cameraSelector: CameraSelector
    private lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var cameraPreview: Preview
    private lateinit var imageAnalysis: ImageAnalysis

    private val cameraXViewModel: CameraXViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.e("TAG", "onCreate: hua code ka  ", )
        cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

        cameraXViewModel.processCameraProvider.observe(this) { provider ->
            processCameraProvider = provider
            bindingCameraPreview()
            bindingInputAnalyser()
        }


    }

    private fun bindingInputAnalyser() {
        val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(
            BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()
        )

        imageAnalysis = ImageAnalysis.Builder()
            .setTargetRotation(binding.previewViewFinder.display.rotation)
            .build()


        val cameraExecutor = Executors.newSingleThreadExecutor()

        imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
            processImageProxy(barcodeScanner, imageProxy)
        }
        try {
            processCameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis)
        } catch (i: IllegalStateException) {
            Log.e("TAG", "bindingInputAnalyser: " + i.message)
        } catch (i: IllegalArgumentException) {
            Log.e("TAG", "bindingInputAnalyser: " + i.message)
        }


    }

    @OptIn(ExperimentalGetImage::class)
    private fun processImageProxy(barcodeScanner: BarcodeScanner, imageProxy: ImageProxy) {
        val inputImage =
            InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)



        barcodeScanner.process(inputImage)
            .addOnSuccessListener { barcodes ->
                if (barcodes.isNotEmpty()) {
                    showBarcodeInfo(barcodes.first())
                }
            }
            .addOnFailureListener {
                Log.e("TAG", "processImageProxy: " + it.message)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    private fun showBarcodeInfo(barcode: com.google.mlkit.vision.barcode.common.Barcode?) {
        when (barcode?.valueType) {


            com.google.mlkit.vision.barcode.common.Barcode.TYPE_URL -> {
                    binding.textviewType.text ="URL"
                binding.textviewResult.text = barcode.rawValue
            }

            com.google.mlkit.vision.barcode.common.Barcode.TYPE_CONTACT_INFO -> {
                binding.textviewType.text ="CONTACT INFO"
                binding.textviewResult.text = barcode.rawValue
            }

            else -> {

            }

        }
    }

    private fun bindingCameraPreview() {
        cameraPreview = Preview.Builder()
            .setTargetRotation(binding.previewViewFinder.display.rotation)
            .build()
        cameraPreview.setSurfaceProvider(binding.previewViewFinder.surfaceProvider)
        try {

        } catch (i: IllegalStateException) {
            Log.e("TAG", "bindingCameraPreview: " + i.message)
        } catch (i: IllegalArgumentException) {
            Log.e("TAG", "bindingCameraPreview: " + i.message)
        }

    }

    companion object{
        private val tag = ScannerActivity::class.simpleName
        fun startScanner(context: Context){
            Intent(context,ScannerActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}