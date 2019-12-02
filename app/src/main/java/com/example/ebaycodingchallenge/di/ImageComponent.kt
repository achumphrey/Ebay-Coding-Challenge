package com.example.ebaycodingchallenge.di

import com.example.ebaycodingchallenge.ui.mainactivity.ImagesMainActivity
import dagger.Component
import dagger.Module
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
@Component(modules= [FragmentModule::class, WebServicesModule::class])
interface ImageComponent {
    fun inject(imagesMainActivity: ImagesMainActivity)
}