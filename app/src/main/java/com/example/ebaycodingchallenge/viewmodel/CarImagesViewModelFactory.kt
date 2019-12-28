package com.example.ebaycodingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ebaycodingchallenge.data.repository.Repository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CarImagesViewModelFactory @Inject constructor(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarImagesViewModel(repository) as T
    }

}