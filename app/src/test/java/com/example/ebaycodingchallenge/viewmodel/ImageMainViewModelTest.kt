package com.example.ebaycodingchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.ebaycodingchallenge.data.model.CarImages
import com.example.ebaycodingchallenge.data.model.Image
import com.example.ebaycodingchallenge.data.repository.ImageRepository
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class ImageMainViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var carImgViewModel: ImageMainViewModel
    private var imageList = mutableListOf<Image>()
    private val carImgLDObserver: Observer<List<Image>> = mock()
    val errorMessageLDObsrever: Observer<String> = mock()
    val loadingStateLDObserver: Observer<ImageMainViewModel.LoadingState> = mock()
    lateinit var carImageObject: CarImages
    private val id = 12

    @Mock
    lateinit var carImgRepository: ImageRepository

    @Before
    fun setUp() {
        carImgViewModel = ImageMainViewModel(carImgRepository)
        imageList.add(Image("anything", "anything"))
        carImageObject = CarImages(id, imageList)
        carImgViewModel.carImage.observeForever(carImgLDObserver)
        carImgViewModel.errorMessage.observeForever(errorMessageLDObsrever)
        carImgViewModel.loadingState.observeForever(loadingStateLDObserver)

    }

    @Test
    fun fetchCarImage_ReturnImage_WithSuccess() {

        `when`(carImgRepository.getCarThumbnailImages()).thenReturn(Single.just(carImageObject))

        carImgViewModel.getCarImageList()

        verify(carImgRepository, atLeast(1)).getCarThumbnailImages()
        verify(carImgLDObserver, atLeast(1)).onChanged(imageList)
        verify(errorMessageLDObsrever, atLeast(0)).onChanged("Any")
        verify(
            loadingStateLDObserver,
            atLeast(1)
        ).onChanged(ImageMainViewModel.LoadingState.SUCCESS)
    }

    @Test
    fun fetchCarImage_NoReturnImage_NullObject() {

        val carObject = CarImages(id, emptyList())

        `when`(carImgRepository.getCarThumbnailImages()).thenReturn(Single.just(carObject))

        carImgViewModel.getCarImageList()

        verify(carImgRepository, atLeast(1)).getCarThumbnailImages()
        verify(carImgLDObserver, atLeast(0)).onChanged(emptyList())
        verify(errorMessageLDObsrever, atLeast(1)).onChanged("No Data Found")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(ImageMainViewModel.LoadingState.ERROR)
    }

    @Test
    fun fetchCarImage_NoReturnImage_NoNetwork() {

        `when`(carImgRepository.getCarThumbnailImages()).thenReturn(
            Single.error(
                UnknownHostException("No Network")
            )
        )

        carImgViewModel.getCarImageList()

        verify(carImgRepository, atLeast(1)).getCarThumbnailImages()
        verify(carImgLDObserver, atLeast(0)).onChanged(null)
        verify(errorMessageLDObsrever, atLeast(1)).onChanged("No Network")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(ImageMainViewModel.LoadingState.ERROR)
    }

    @Test
    fun fetchCarImage_NoReturnImage_WithError() {

        `when`(carImgRepository.getCarThumbnailImages()).thenReturn(Single.error(RuntimeException("Something Wrong")))

        carImgViewModel.getCarImageList()

        verify(carImgRepository, atLeast(1)).getCarThumbnailImages()
        verify(carImgLDObserver, atLeast(0)).onChanged(null)
        verify(errorMessageLDObsrever, atLeast(1)).onChanged("Something Wrong")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(ImageMainViewModel.LoadingState.ERROR)
    }

    @After
    fun tearDown() {
    }
}