package com.dexter.dunzo.ui.main.api

import com.dexter.dunzo.ui.main.api.models.FeedDetails
import io.reactivex.Single

interface IApiClient {
    fun getItems( term: String, pageCount: Int): Single<FeedDetails>
}
