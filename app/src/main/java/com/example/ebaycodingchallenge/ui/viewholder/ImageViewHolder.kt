package com.example.ebaycodingchallenge.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ebaycodingchallenge.data.model.Image
import com.example.ebaycodingchallenge.ui.adapter.ImageClickListener
import com.example.ebaycodingchallenge.util.loadGlideImage
import kotlinx.android.synthetic.main.image_holder_images.view.*

class ImageViewHolder(item:View): RecyclerView.ViewHolder(item) {
    fun bindItem(images: Image, listener: ImageClickListener){
        itemView.imgCar.loadGlideImage("https://${images.uri}_2.jpg")
        itemView.setOnClickListener {
            listener.imageClickListener(images)
        }

    }
}