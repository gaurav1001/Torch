package com.example.torch

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var toggle:ToggleButton
    private lateinit var cameraManager:CameraManager
    private lateinit var getCameraID:String
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggle = findViewById(R.id.torch)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            // O means back camera unit,
            // 1 means front camera unit
            getCameraID = cameraManager.cameraIdList[0]
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

    // Log.d("AAA","${torchButton.isClickable}")

        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                try {
                    cameraManager.setTorchMode(getCameraID,true)
                }catch (e:Exception){
                    e.printStackTrace()
                }

            } else {
                try {
                    cameraManager.setTorchMode(getCameraID,false)
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun finish() {
        super.finish()
        try {
            cameraManager.setTorchMode(getCameraID,false)
            Log.d("OFF","camera-off")
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}