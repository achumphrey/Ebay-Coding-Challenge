package com.example.ebaycodingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ebaycodingchallenge.data.repository.Repository

class CarImagesViewModelFactory constructor(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarImagesViewModel(repository) as T
    }

}