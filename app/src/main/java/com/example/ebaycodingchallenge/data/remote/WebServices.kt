package com.example.ebaycodingchallenge.data.remote

import com.example.ebaycodingchallenge.data.model.CarImages
import com.example.ebaycodingchallenge.util.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface WebServices {
    @GET(Constants.ENDPOINT_URL)
    fun getThumbnailImages(): Single<CarImages>
}