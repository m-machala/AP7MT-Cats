package com.example.cats

import android.content.Context
import android.graphics.BitmapFactory
import java.io.File

class DownloadedActivityViewModel(private val context: Context) {

    fun getCatList(): MutableList<Cat> {
        val dir = context.filesDir
        val files = dir.listFiles()
        val outputList = mutableListOf<Cat>()

        for(file in files) {
            if(file == null) {
                continue
            }
            val bytes = file.readBytes()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            val name = file.name.substring(0, file.name.length - 4)
            val newCat = Cat(bitmap, name)
            outputList.add(newCat)
        }
        return outputList
    }
}