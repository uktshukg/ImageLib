package com.dexter.dunzo.ui.main.api

import com.dexter.dunzo.ui.main.FeedDetails
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Error
import javax.inject.Inject

class ApiClientImpl @Inject constructor(private val apiClient: ApiClient): IApiClient {
    override fun getItems(term: String, pageCount: Int): Single<FeedDetails> {
        return apiClient.getItems(text = term, paginationCount = pageCount).map {
            if(it.isSuccessful && it.body()?.stat=="ok"){
                it.body()
            }else throw Error(it.message())
        }
    }
}