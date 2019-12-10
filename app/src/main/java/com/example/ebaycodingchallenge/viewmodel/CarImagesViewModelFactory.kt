package com.example.ebaycodingchallenge.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ebaycodingchallenge.data.repository.Repository

class CarImagesViewModelFactory constructor(private val repository: Repository, val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarImagesViewModel(repository, application) as T
    }

}