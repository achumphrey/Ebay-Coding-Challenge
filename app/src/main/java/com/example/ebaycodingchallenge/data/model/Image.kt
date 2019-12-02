package com.example.ebaycodingchallenge.data.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("set")
    val `set`: String,
    @SerializedName("uri")
    val uri: String
)