package com.example.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cats.databinding.ActivityViewNewImageBinding
import android.graphics.BitmapFactory

class ViewNewImage : AppCompatActivity() {
    private lateinit var binding: ActivityViewNewImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewNewImageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("Name")
        val image = intent.getByteArrayExtra("Image")
        val bitmap = image?.let { BitmapFactory.decodeByteArray(image, 0, it.size) }

        binding.imageView.setImageBitmap(bitmap)

        setContentView(binding.root)
    }
}