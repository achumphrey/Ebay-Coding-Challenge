package com.example.ebaycodingchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ebaycodingchallenge.data.model.Image
import com.example.ebaycodingchallenge.data.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class CarImagesViewModel constructor(private val repository: Repository): ViewModel() {
    private val  disposable:CompositeDisposable = CompositeDisposable()
    val carImage: MutableLiveData<List<Image>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val loadingState = MutableLiveData<LoadingState>()

    fun getCarImageList(){
        loadingState.value = LoadingState.LOADING
        disposable.add(
        repository.getCarThumbnailImages()
            .subscribe({
                if (it.images.isEmpty()){
                    errorMessage.value = "No Data Found"
                    loadingState.value = LoadingState.ERROR
                }else{
                    carImage.value = it.images
                    loadingState.value = LoadingState.SUCCESS
                }
            },{
                it.printStackTrace()
                when (it) {
                    is UnknownHostException -> errorMessage.value = "No Network"
                    else -> errorMessage.value = it.localizedMessage
                }
                loadingState.value = LoadingState.ERROR
            })

        )
    }

    enum class LoadingState {
        LOADING,
        SUCCESS,
        ERROR
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}