package com.example.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cats.databinding.ActivityDownloadedBinding

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
}