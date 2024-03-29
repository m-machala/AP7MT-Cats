package com.example.cats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DownloadedRecyclerViewAdapter(private val catList: List<Cat>, private val ref: DownloadedActivity) :
    RecyclerView.Adapter<DownloadedRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = catList[position]
        holder.catImageView.setImageBitmap(cat.bitmap)
        holder.catTextView.text = cat.name
        holder.catImageView.setOnClickListener {
            ref.openImage(cat)
        }
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val catImageView: ImageView = itemView.findViewById(R.id.image)
        val catTextView: TextView = itemView.findViewById(R.id.text)
    }
}