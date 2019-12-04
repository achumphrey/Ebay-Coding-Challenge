package com.example.ebaycodingchallenge.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ebaycodingchallenge.R
import com.example.ebaycodingchallenge.data.model.Image
import com.example.ebaycodingchallenge.di.DaggerImageComponent
import com.example.ebaycodingchallenge.di.RepositoryModule
import com.example.ebaycodingchallenge.di.WebServicesModule
import com.example.ebaycodingchallenge.ui.adapter.CarImageAdapter
import com.example.ebaycodingchallenge.ui.adapter.GridSpacingItemDecoration
import com.example.ebaycodingchallenge.ui.adapter.ImageClickListener
import com.example.ebaycodingchallenge.viewmodel.CarImagesViewModel
import com.example.ebaycodingchallenge.viewmodel.CarImagesViewModelFactory
import kotlinx.android.synthetic.main.activity_cars.*
import javax.inject.Inject

class CarActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: CarImagesViewModelFactory
    lateinit var viewModel: CarImagesViewModel
    lateinit var imageAdapter: CarImageAdapter
    companion object{const val INTENT_MESSAGE = "message"}
    private val carImageClickListener: ImageClickListener = object : ImageClickListener {

        override fun imageClickListener(image: Image) {
            intent = Intent(this@CarActivity, CarDetailsActivity::class.java)
            intent.putExtra(INTENT_MESSAGE, "https://${image.uri}_27.jpg")
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars)

        getDependency()
        setupRecyclerView()

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CarImagesViewModel::class.java)

        viewModel.carImage.observe(this, Observer {
            imageAdapter.updateImage(it)

        })

        viewModel.errorMessage.observe(this, Observer {
            tvErrorMessage.text = it
        })

        viewModel.loadingState.observe(this, Observer {
            when (it) {
                CarImagesViewModel.LoadingState.LOADING -> displayProgressbar()
                CarImagesViewModel.LoadingState.SUCCESS -> displayImageList()
                CarImagesViewModel.LoadingState.ERROR -> displayErrorMessage()
                else -> displayErrorMessage()
            }
        })

        viewModel.getCarImageList()

        btnRetry.setOnClickListener {
            viewModel.getCarImageList()
        }
    }

    fun getDependency() {
        DaggerImageComponent.builder()
            .repositoryModule(RepositoryModule())
            .webServicesModule(WebServicesModule())
            .build()
            .inject(this)
    }

    private fun displayProgressbar() {
        prgBar.visibility = View.VISIBLE
        rvCarImages.visibility = View.GONE
        tvErrorMessage.visibility = View.GONE
        btnRetry.visibility = View.GONE
    }

    private fun displayErrorMessage() {
        tvErrorMessage.visibility = View.VISIBLE
        btnRetry.visibility = View.VISIBLE
        rvCarImages.visibility = View.GONE
        prgBar.visibility = View.GONE
    }

    private fun displayImageList() {
        tvErrorMessage.visibility = View.GONE
        btnRetry.visibility = View.GONE
        rvCarImages.visibility = View.VISIBLE
        prgBar.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        rvCarImages.layoutManager = GridLayoutManager(this, 2)
        rvCarImages.addItemDecoration(GridSpacingItemDecoration(2, 20, true))
        imageAdapter = CarImageAdapter(mutableListOf(), carImageClickListener)
        rvCarImages.adapter = imageAdapter
    }
}
