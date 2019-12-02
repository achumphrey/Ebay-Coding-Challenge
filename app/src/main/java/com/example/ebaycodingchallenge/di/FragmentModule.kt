package com.example.ebaycodingchallenge.di

import com.example.ebaycodingchallenge.data.remote.WebServices
import com.example.ebaycodingchallenge.data.repository.ImageRepoImp
import com.example.ebaycodingchallenge.data.repository.ImageRepository
import com.example.ebaycodingchallenge.viewmodel.ImageMainViewModel
import com.example.ebaycodingchallenge.viewmodel.ImageViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FragmentModule {

    @Singleton
    @Provides
    fun provideViewModelFactory (repository: ImageRepository):ImageViewModelFactory{
        return  ImageViewModelFactory(repository)
    }

    @Singleton
    @Provides
    fun provideImageRepository(webServices: WebServices): ImageRepository{
        return ImageRepoImp(webServices )
    }
}