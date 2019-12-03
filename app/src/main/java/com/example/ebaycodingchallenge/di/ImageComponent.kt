package com.example.ebaycodingchallenge.di

import com.example.ebaycodingchallenge.ui.mainactivity.CarActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules= [RepositoryModule::class, WebServicesModule::class])
interface ImageComponent {
    fun inject(carActivity: CarActivity)
}