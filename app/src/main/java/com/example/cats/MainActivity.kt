package com.example.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cats.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var viewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buttonRefresh.setOnClickListener {
            val scope = CoroutineScope(Dispatchers.Default)
            scope.launch {
                val cat = viewModel.getCat()
                withContext(Dispatchers.Main) {
                    binding.imageView2.setImageBitmap(cat.bitmap)
                }
            }
        }
        setContentView(binding.root)
    }
}