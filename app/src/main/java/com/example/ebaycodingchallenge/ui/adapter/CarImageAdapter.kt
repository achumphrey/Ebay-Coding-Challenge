package com.example.ebaycodingchallenge.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebaycodingchallenge.R
import com.example.ebaycodingchallenge.data.model.Image
import com.example.ebaycodingchallenge.ui.viewholder.ImageViewHolder
import com.example.ebaycodingchallenge.util.inflate

class CarImageAdapter constructor(private var images: MutableList<Image>, val listener: ImageClickListener):
    RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view : View = parent.inflate(R.layout.image_holder_images, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindItem(images[position], listener)
    }

    fun updateImage(newImageList: List<Image>){
        images.clear()
        images.addAll(newImageList)
        notifyDataSetChanged()
    }
}