package com.example.cats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cats.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainRecyclerViewAdapter
    private var viewModel = MainActivityViewModel()
    private var catList = mutableListOf<Cat>()
    private var urlModifier = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buttonRefresh.setOnClickListener {
            loadCats()
        }

        binding.buttonSaved.setOnClickListener {
            val i = Intent(this, DownloadedActivity::class.java)
            startActivity(i)
        }

        binding.buttonSettings.setOnClickListener {
            val i = Intent(this, SettingsActivity::class.java)
            startActivityForResult(i, 1)
        }

        binding.recycler.layoutManager = LinearLayoutManager(this)
        adapter = MainRecyclerViewAdapter(catList, this)
        binding.recycler.adapter = adapter
        loadCats()

        setContentView(binding.root)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null) {
            val result = data.getStringExtra("urlModifier")
            if(result != null) {
                urlModifier = result
            }
            else {
                urlModifier = ""
            }
        }
        else {
            urlModifier = ""
        }
        loadCats()
    }
    private fun loadCats() {
        if(catList.size > 0) {
            val size = catList.size
            for(i in 1 .. size) {
                catList.removeAt(0)
                adapter.notifyItemRemoved(0)
            }
        }
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            for(i in 0 .. 9) {
                val cat = viewModel.getCat(urlModifier)
                withContext(Dispatchers.Main) {
                    catList.add(cat)
                    adapter.notifyItemInserted(catList.size)
                }
            }
        }
    }

    fun openImage(cat: Cat) {
        val i = Intent(this, ViewNewImageActivity::class.java)
        i.putExtra("Name", cat.name)
        val os = ByteArrayOutputStream()
        val bitmapCopy = cat.bitmap?.copy(cat.bitmap.config, true)
        bitmapCopy?.compress(Bitmap.CompressFormat.JPEG, 50, os)
        val arr = os.toByteArray()
        os.close()
        i.putExtra("Image", arr)
        startActivity(i)
    }
}