package com.ordersexample.mytabkotlinapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Trailer(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("iso_3166_1")
    val iso_3166_1: String,
    @Expose
    @SerializedName("iso_639_1")
    val iso_639_1: String,
    @Expose
    @SerializedName("key")
    val key: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("site")
    val site: String,
    @Expose
    @SerializedName("size")
    val size: Int,
    @Expose
    @SerializedName("type")
    val type: String
):Serializable