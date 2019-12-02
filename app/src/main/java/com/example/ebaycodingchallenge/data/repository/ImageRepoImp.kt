package com.example.ebaycodingchallenge.data.repository

import com.example.ebaycodingchallenge.data.model.CarImages
import com.example.ebaycodingchallenge.data.remote.WebServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageRepoImp @Inject constructor(private val webServices: WebServices):ImageRepository {
    override fun getCarThumbnailImages(): Single<CarImages> {
        return webServices.getThumbnailImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}