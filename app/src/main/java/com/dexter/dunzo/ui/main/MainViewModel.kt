package com.dexter.dunzo.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dexter.dunzo.ui.main.api.Const
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getItems: GetItems,
    private val model: MainModel,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {


    private val mutablelIst = MutableLiveData<MainModel>()

    private val wordSubject: PublishSubject<String> = PublishSubject.create()
    val _list: LiveData<MainModel>
        get() = mutablelIst

    init {
        compositeDisposable.add(wordSubject.throttleLast(800, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter { t: String -> t.isNotBlank() }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).flatMapSingle {
                model.searchTerm = it
                model.paginationCount = Const.INITIAL_PAGE_COUNT
                model.isLoading = true
                model.isLastPage = false
                getItems.getItems(it, model.paginationCount)
            }.subscribe { t1: LocalFeedDetails? ->
                t1?.let {
                    model.isLoading = false
                    if (t1.photos.size < Const.PERPAGE_COUNT) {
                        model.isLastPage = true
                    }
                    val diffInfo = DiffFinder.compare(model.itemsList, ArrayList(it.photos))
                    model.diffInfo = diffInfo
                    model.itemsList = it.photos
                    mutablelIst.postValue(model)
                }
            })
    }

    fun fetchItems(term: String) {
        wordSubject.onNext(term)
    }

    fun continueFetch() {
        model.isLoading = true
        model.paginationCount++
        compositeDisposable.add(getItems.getItems(model.searchTerm, model.paginationCount)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { t1: LocalFeedDetails?, t2: Throwable? ->
                t1?.let {
                    model.isLoading = false
                    if (it.photos.size < Const.PERPAGE_COUNT) {
                        model.isLastPage = true
                    }
                    if (it.photos.isEmpty().not()) {
                        val oldListSize = model.itemsList.size
                        model.diffInfo = DiffFinder.DiffInfo(DiffFinder.Range.Added, oldListSize,it.photos.size)
                        model.itemsList.addAll(it.photos)
                        mutablelIst.postValue(model)
                    }
                }

            })
    }


}