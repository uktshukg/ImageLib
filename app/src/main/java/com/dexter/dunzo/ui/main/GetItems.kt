package com.dexter.dunzo.ui.main

import com.dexter.dunzo.ui.main.api.ApiClient
import com.dexter.dunzo.ui.main.api.IApiClient
import io.reactivex.Single
import javax.inject.Inject

class GetItems @Inject constructor(private val apiClientImpl: IApiClient) {

    fun getItems(term: String, paginationCount: Int) : Single<LocalFeedDetails> {
        return apiClientImpl.getItems(  term,   paginationCount).map {
            val list = ArrayList<LocalPhoto>()
            it.photos.photo.forEach {
                ApiConvertor.LOCAL_PHOTO.convert(it )?.let { it1 -> list.add(it1) }
            }
            return@map LocalFeedDetails(list)
        }
    }
}