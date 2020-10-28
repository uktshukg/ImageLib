package com.dexter.dunzo.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dexter.dunzo.R

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var photoIV: ImageView = itemView.findViewById<ImageView>(R.id.photo)
    private var title: TextView = itemView.findViewById<TextView>(R.id.title)
    fun bind(photo: LocalPhoto) {
       Glide.with(itemView.context).load(photo.imageURL).into(photoIV)
        title.text = photo.title
    }

}
