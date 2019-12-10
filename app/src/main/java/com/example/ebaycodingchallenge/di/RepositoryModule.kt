package com.example.ebaycodingchallenge.di

import android.app.Application
import com.example.ebaycodingchallenge.data.remote.WebServices
import com.example.ebaycodingchallenge.data.repository.RepoImp
import com.example.ebaycodingchallenge.data.repository.Repository
import com.example.ebaycodingchallenge.viewmodel.CarImagesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideViewModelFactory (repository: Repository, application: Application):CarImagesViewModelFactory{
        return  CarImagesViewModelFactory(repository, application)
    }

    @Singleton
    @Provides
    fun provideImageRepository(webServices: WebServices): Repository{
        return RepoImp(webServices )
    }

    @Singleton
    @Provides
    fun provideApplication(): Application{
        return Application()
    }
}