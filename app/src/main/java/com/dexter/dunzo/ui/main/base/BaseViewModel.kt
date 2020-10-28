package com.dexter.dunzo.ui.main.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel  (private val compositeDisposable: CompositeDisposable) : ViewModel(){

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
