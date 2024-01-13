package com.example.cats

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cats.databinding.ActivityViewNewImageBinding
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

class ViewNewImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewNewImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewNewImageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("Name")
        val image = intent.getByteArrayExtra("Image")
        val bitmap = image?.let { BitmapFactory.decodeByteArray(image, 0, it.size) }

        binding.imageView.setImageBitmap(bitmap)

        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.buttonSave.setOnClickListener {
            var file = File(filesDir, "$name.jpg")
            val os = FileOutputStream(file)
            val bitmapCopy = bitmap?.copy(bitmap.config, true)
            bitmapCopy?.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.close()

            finish()
        }

        setContentView(binding.root)
    }
}