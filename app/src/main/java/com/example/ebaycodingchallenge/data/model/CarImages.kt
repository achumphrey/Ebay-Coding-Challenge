package com.example.ebaycodingchallenge.data.model


import com.google.gson.annotations.SerializedName


data class CarImages(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>
)