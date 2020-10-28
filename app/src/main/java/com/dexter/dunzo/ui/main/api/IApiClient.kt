package com.dexter.dunzo.ui.main.api

import com.dexter.dunzo.ui.main.FeedDetails
import io.reactivex.Completable
import io.reactivex.Single

interface IApiClient {
    fun getItems( term: String, pageCount: Int): Single<FeedDetails>
}
