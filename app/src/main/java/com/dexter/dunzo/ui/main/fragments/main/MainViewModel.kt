package com.dexter.dunzo.ui.main.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dexter.dunzo.ui.main.utilities.Const
import com.dexter.dunzo.ui.main.base.BaseViewModel
import com.dexter.dunzo.ui.main.fragments.main.model.LocalFeedDetails
import com.dexter.dunzo.ui.main.fragments.main.use_case.GetItems
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


    private val mutablelist = MutableLiveData<MainModel>()

    private val wordSubject: PublishSubject<String> = PublishSubject.create()
    val _list: LiveData<MainModel>
        get() = mutablelist

    init {
        compositeDisposable.add(wordSubject.throttleLast(800, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter { t: String -> t.isNotBlank() }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).flatMapSingle {
                model.searchTerm = it
                model.paginationCount =
                    Const.INITIAL_PAGE_COUNT
                model.isLoading = true
                model.isLastPage = false
                getItems.getItems(it, model.paginationCount)
            }.subscribe { t1: LocalFeedDetails? ->
                t1?.let {
                    model.isLoading = false
                    model.itemsList = it.photos
                    if (model.itemsList.size == it.total.toInt()) {
                        model.isLastPage = true
                    }
                    mutablelist.postValue(model)
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
                    if (it.photos.isEmpty().not()) {
                        model.itemsList.addAll(it.photos)
                        if (model.itemsList.size == it.total.toInt()) {
                            model.isLastPage = true
                        }
                        mutablelist.postValue(model)
                    }
                }

            })
    }


}