package com.dexter.dunzo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexter.dunzo.R
import com.dexter.dunzo.ui.main.api.Const
import javax.inject.Inject


class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var model: MainModel
    private lateinit var rycView: RecyclerView
    private lateinit var adapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PhotoAdapter()
        adapter.setHasStableIds(true)
        rycView = view.findViewById<RecyclerView>(R.id.ryc_view)
        rycView.setItemViewCacheSize(3)
        rycView.adapter = adapter
        val layoutManager = rycView.layoutManager!! as LinearLayoutManager
        rycView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                model.let {
                    Log.e("utkarsh","inside "+it.isLoading +" last page "+it.isLastPage+"  visibleItemCount "+visibleItemCount+" totalItemCount "+totalItemCount+" firstVisibleItemPosition "+firstVisibleItemPosition)
                    if (!it.isLoading && !it.isLastPage) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount-Const.PRECACHE_SIZE
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= Const.PAGE_SIZE
                        ) {
                            loadMoreItems()
                        }
                    }
                }
            }
        })
        viewModel._list.observe(viewLifecycleOwner, Observer { it    ->
            this.model = it
            it.diffInfo?.let {
                when (it.range) {
                    DiffFinder.Range.Same -> {
                    }
                    DiffFinder.Range.Removed -> {
                        Log.e("utkarsh","inside removed "+model.itemsList.size +"  index "+it.index+" itemcount "+it.itemCount)
                        adapter.setPhotos(model.itemsList)
                        adapter.notifyItemRangeRemoved(it.index,it.itemCount)
                    }
                    DiffFinder.Range.Added -> {
                        Log.e("utkarsh","inside added "+model.itemsList.size +"  index "+it.index+"it.itemCount "+it.itemCount)
                        adapter.setPhotos(model.itemsList)
                        adapter.notifyItemRangeInserted(it.index,it.itemCount)
                    }
                    DiffFinder.Range.Complex -> {
                        adapter.setPhotos(model.itemsList)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
        view.findViewById<EditText>(R.id.edit_query).addTextChangedListener {
            it?.let {
                viewModel.fetchItems(it.toString())
            }
        }
    }

    private fun loadMoreItems() {
        viewModel.continueFetch()
    }
}