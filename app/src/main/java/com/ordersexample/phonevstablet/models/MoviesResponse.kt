package com.ordersexample.mytabkotlinapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesResponse(
    @Expose
    @SerializedName("page")
    val page: Int,
    @Expose
    @SerializedName("results")
    val movies: List<Movie>,
    @Expose
    @SerializedName("total_pages")
    val total_pages: Int,
    @Expose
    @SerializedName("total_results")
    val total_results: Int
):Serializable
