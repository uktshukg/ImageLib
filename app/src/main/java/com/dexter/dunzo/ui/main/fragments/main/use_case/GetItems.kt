package com.dexter.dunzo.ui.main.fragments.main.use_case

import com.dexter.dunzo.ui.main.api.ApiConverter
import com.dexter.dunzo.ui.main.fragments.main.model.LocalFeedDetails
import com.dexter.dunzo.ui.main.fragments.main.model.LocalPhoto
import com.dexter.dunzo.ui.main.api.IApiClient
import io.reactivex.Single
import javax.inject.Inject

class GetItems @Inject constructor(private val apiClientImpl: IApiClient) {

    fun getItems(term: String, paginationCount: Int) : Single<LocalFeedDetails> {
        return apiClientImpl.getItems(  term,   paginationCount).map {
            val list = ArrayList<LocalPhoto>()
            it.photos.photo.forEach {
                ApiConverter.LOCAL_PHOTO.convert(it )?.let { it1 -> list.add(it1) }
            }
            return@map LocalFeedDetails(
                list,
                it.photos.total
            )
        }
    }
}