package com.ordersexample.mytabkotlinapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailersResponse(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("results")
    val trailers: List<Trailer>
)