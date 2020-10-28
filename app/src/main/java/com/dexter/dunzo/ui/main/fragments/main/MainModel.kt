package com.dexter.dunzo.ui.main.fragments.main

import com.dexter.dunzo.ui.main.fragments.main.model.LocalPhoto
import javax.inject.Inject

class MainModel @Inject constructor() {

    var searchTerm: String = ""
    var paginationCount: Int = 0
    var itemsList: ArrayList<LocalPhoto> = ArrayList()
    var isLoading: Boolean = false
    var isLastPage: Boolean = false

}