package com.example.cats

import android.graphics.Bitmap
import java.net.URL
import kotlin.io.readBytes
import android.graphics.BitmapFactory

class MainActivityViewModel {

    suspend fun getCat(): Cat {
        var bitmap: Bitmap? = null
        try {
            val url = URL("https://cataas.com/cat")
            val imageData = url.readBytes()
            bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        }
        catch (_: Exception) {
        }

        val name = "test name"
        return Cat(bitmap, name)
    }
}