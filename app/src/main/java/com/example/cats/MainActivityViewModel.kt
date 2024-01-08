package com.example.cats

import android.graphics.Bitmap
import java.net.URL
import kotlin.io.readBytes
import kotlinx.coroutines.*
import android.graphics.BitmapFactory

class MainActivityViewModel {

    private suspend fun downloadCat(): Bitmap? {
        val scope = CoroutineScope(Dispatchers.Default)
        var bitmap: Bitmap? = null
        try {
            val url = URL("https://cataas.com/cat")
            val imageData = url.readBytes()
            bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        }
        catch (_: Exception) {
        }

        return bitmap
    }

    suspend fun getCat(): Cat {
        val bitmap = downloadCat()
        val name = "test name"

        return Cat(bitmap, name)
    }
}