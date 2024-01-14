package com.example.cats

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cats.databinding.ActivityViewDownloadedImageBinding
import java.io.File
import java.io.FileOutputStream

class ViewDownloadedImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewDownloadedImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewDownloadedImageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("Name")
        val image = intent.getByteArrayExtra("Image")
        val bitmap = image?.let { BitmapFactory.decodeByteArray(image, 0, it.size) }

        binding.imageView.setImageBitmap(bitmap)

        binding.buttonBack.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }
}