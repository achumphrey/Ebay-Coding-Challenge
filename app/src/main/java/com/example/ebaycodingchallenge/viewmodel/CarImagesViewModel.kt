package com.example.ebaycodingchallenge.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ebaycodingchallenge.R
import com.example.ebaycodingchallenge.data.model.Image
import com.example.ebaycodingchallenge.data.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class CarImagesViewModel @Inject constructor(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val carImage: MutableLiveData<List<Image>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val loadingState = MutableLiveData<LoadingState>()
    private val context: Context
        get() = getApplication<Application>().applicationContext

    fun getCarImageList() {
        loadingState.value = LoadingState.LOADING
        disposable.add(
            repository.getCarThumbnailImages()
                .subscribe({
                    if (it.images.isEmpty()) {
                        errorMessage.value = context.getString(R.string.no_data_found)
                        loadingState.value = LoadingState.ERROR
                    } else {
                        carImage.value = it.images
                        loadingState.value = LoadingState.SUCCESS
                    }
                }, {
                    it.printStackTrace()
                    when (it) {
                        is UnknownHostException -> errorMessage.value =
                            context.getString(R.string.no_network)
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