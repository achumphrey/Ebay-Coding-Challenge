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
import com.example.ebaycodingchallenge.viewmodel.ImageMainViewModel
import com.example.ebaycodingchallenge.viewmodel.ImageViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CarActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ImageViewModelFactory
    lateinit var viewModel: ImageMainViewModel
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
        setContentView(R.layout.activity_main)

        getDepenedency()
        setupRecyclerView()

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ImageMainViewModel::class.java)

        viewModel.carImage.observe(this, Observer {
            imageAdapter.updateImage(it)

        })

        viewModel.errorMessage.observe(this, Observer {
            tvErrorMessage.text = it
        })

        viewModel.loadingState.observe(this, Observer {
            when (it) {
                ImageMainViewModel.LoadingState.LOADING -> displayProgressbar()
                ImageMainViewModel.LoadingState.SUCCESS -> displayImageList()
                ImageMainViewModel.LoadingState.ERROR -> displayErrorMessage()
                else -> displayErrorMessage()
            }
        })

        viewModel.getCarImageList()
    }

    fun getDepenedency() {
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
    }

    private fun displayErrorMessage() {
        tvErrorMessage.visibility = View.VISIBLE
        rvCarImages.visibility = View.GONE
        prgBar.visibility = View.GONE
    }

    private fun displayImageList() {

        tvErrorMessage.visibility = View.GONE
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
