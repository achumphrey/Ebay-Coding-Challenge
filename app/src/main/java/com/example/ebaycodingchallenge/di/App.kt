package com.example.ebaycodingchallenge.di

import android.app.Application

class App : Application() {

    companion object {
        fun getComponent() = ImageComponent.instance
    }

    override fun onCreate() {
        super.onCreate()
        ImageComponent.instance = buildComponent()
    }

    private fun buildComponent(): ImageComponent {
      return DaggerImageComponent.builder()
            .repositoryModule(RepositoryModule())
            .webServicesModule(WebServicesModule())
            .build()
    }

}