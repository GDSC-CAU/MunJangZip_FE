package com.example.munjangzip.feature.savebook


import android.app.Activity
import android.widget.Toast
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

fun Activity.startMLKitScanner() {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC)
    .enableAutoZoom() // available on 16.1.0 and higher
        .build()

    val scanner = GmsBarcodeScanning.getClient(this)

    var ISBNnumber = null
// Or with a configured options
// val scanner = GmsBarcodeScanning.getClient(this, options)

    scanner.startScan()
        .addOnSuccessListener { barcode ->
            // Task completed successfully
            val rawValue: String? = barcode.rawValue
            rawValue?.let {
                // 바코드 값 처리
                //println("Scanned barcode: $it")
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show() //바코드값 테스트 용으로 메시지창
            }
        }
        .addOnCanceledListener {
            // Task canceled
        }
        .addOnFailureListener { e ->
            // Task failed with an exception
        }

}


