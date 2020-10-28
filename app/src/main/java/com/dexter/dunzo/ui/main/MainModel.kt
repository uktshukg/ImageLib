package com.dexter.dunzo.ui.main

import javax.inject.Inject

class MainModel @Inject constructor() {

    var diffInfo: DiffFinder.DiffInfo? = null
    var searchTerm: String = ""
    var paginationCount: Int = 0
    var itemsList: ArrayList<LocalPhoto> = ArrayList()
    var isLoading: Boolean = false
    var isLastPage: Boolean = false

}