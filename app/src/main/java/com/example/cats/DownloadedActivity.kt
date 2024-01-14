package com.example.cats

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cats.databinding.ActivityDownloadedBinding
import java.io.ByteArrayOutputStream

class DownloadedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDownloadedBinding
    private lateinit var adapter: DownloadedRecyclerViewAdapter
    private var viewModel = DownloadedActivityViewModel(this)
    private var catList = mutableListOf<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadedBinding.inflate(layoutInflater)

        catList = viewModel.getCatList()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        adapter = DownloadedRecyclerViewAdapter(catList, this)
        binding.recycler.adapter = adapter

        binding.buttonBack.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val newCatList = viewModel.getCatList()
        catList.clear()
        catList.addAll(newCatList)
        adapter.notifyDataSetChanged()
    }

    fun openImage(cat: Cat) {
        val i = Intent(this, ViewDownloadedImageActivity::class.java)
        i.putExtra("Name", cat.name)
        val os = ByteArrayOutputStream()
        val bitmapCopy = cat.bitmap?.copy(cat.bitmap.config, true)
        bitmapCopy?.compress(Bitmap.CompressFormat.JPEG, 100, os)
        val arr = os.toByteArray()
        os.close()
        i.putExtra("Image", arr)
        startActivity(i)
    }
}