package com.example.ebaycodingchallenge.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ebaycodingchallenge.R
import com.example.ebaycodingchallenge.util.loadGlideImage
import kotlinx.android.synthetic.main.activity_car_details.*

class CarDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)

        var carImage : String = intent.getStringExtra ("message")
        imgLarge.loadGlideImage(carImage)
    }
}
