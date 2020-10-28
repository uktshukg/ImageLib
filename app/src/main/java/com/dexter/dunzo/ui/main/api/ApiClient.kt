package com.dexter.dunzo.ui.main.api

import com.dexter.dunzo.ui.main.FeedDetails
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiClient {
    @GET(".")
    fun getItems(  @Query("method") method: String ="flickr.photos.search",
                   @Query("text") text: String,
                   @Query("per_page") perpage: Int= Const.PERPAGE_COUNT,
                   @Query("page") paginationCount: Int = 1): Single<Response<FeedDetails>>
}