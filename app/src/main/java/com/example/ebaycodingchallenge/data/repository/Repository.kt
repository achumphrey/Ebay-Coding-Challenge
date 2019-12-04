package com.example.ebaycodingchallenge.data.repository

import com.example.ebaycodingchallenge.data.model.CarImages
import io.reactivex.Single

interface Repository {
    fun getCarThumbnailImages(): Single<CarImages>
}