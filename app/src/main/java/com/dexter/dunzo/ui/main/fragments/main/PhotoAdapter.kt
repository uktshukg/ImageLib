package com.dexter.dunzo.ui.main.fragments.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dexter.dunzo.R
import com.dexter.dunzo.ui.main.fragments.main.model.LocalPhoto

class PhotoAdapter: RecyclerView.Adapter<PhotoViewHolder>() {
    var photos = ArrayList<LocalPhoto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_photo_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return  photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
      holder.bind(photos[position])
    }

    fun setPhotos(it: List<LocalPhoto>) {
        photos.clear()
        photos.addAll(it)
    }

    override fun getItemId(position: Int): Long {
        // assuming id unique for all
        return photos[position].id.toLong()
    }

    fun getItemAt(position: Int): LocalPhoto? {
        if(photos.size>position){
            return photos[position]
        }
        return  null;
    }
}
