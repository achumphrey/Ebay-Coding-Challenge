package com.example.ebaycodingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ebaycodingchallenge.data.repository.ImageRepoImp
import com.example.ebaycodingchallenge.data.repository.ImageRepository

class ImageViewModelFactory constructor(private val repository: ImageRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageMainViewModel(repository) as T
    }

}