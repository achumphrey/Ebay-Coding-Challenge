package com.example.ebaycodingchallenge.di

import android.app.Application

class App : Application() {

    companion object {
       private lateinit var component: ImageComponent
       fun getComponent() = component
    }

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
    }

    private fun buildComponent(): ImageComponent {
      return DaggerImageComponent.builder()
            .repositoryModule(RepositoryModule())
            .webServicesModule(WebServicesModule())
            .build()
    }

}