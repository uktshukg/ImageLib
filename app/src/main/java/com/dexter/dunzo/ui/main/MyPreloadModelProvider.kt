package com.dexter.dunzo.ui.main

import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder
import com.dexter.dunzo.ui.main.fragments.main.PhotoAdapter
import com.dexter.dunzo.ui.main.fragments.main.model.LocalPhoto
import java.util.*


class MyPreloadModelProvider(
    private var recyclerView: RecyclerView,
    val frag: Fragment
) : PreloadModelProvider<LocalPhoto> {
    override fun getPreloadItems(position: Int): MutableList<LocalPhoto> {
        val local = (recyclerView.adapter as PhotoAdapter).getItemAt(position)
        if (local!=null) {
            val url: String? = local.imageURL
            url?.let {
                return@let if (TextUtils.isEmpty(it)) {
                    Collections.emptyList()
                } else Collections.singletonList(it)
            }
            return Collections.emptyList()
        } else {
            return Collections.emptyList()
        }
    }

    override fun getPreloadRequestBuilder(item: LocalPhoto): RequestBuilder<*>? {
        return Glide.with(frag)
            .load(item.imageURL)
    }


}
