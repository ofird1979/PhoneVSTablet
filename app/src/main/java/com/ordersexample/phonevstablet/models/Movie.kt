package com.ordersexample.mytabkotlinapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.lang.reflect.Constructor
import java.util.*
@Parcelize
data class Movie(
//    @Expose
//    @SerializedName("adult")
//    val adult: Boolean,
//    @Expose
//    @SerializedName("backdrop_path")
//    val backdrop_path: String,
//    @Expose
//    @SerializedName("genre_ids")
//    val genre_ids: List<Int>,
    @Expose
    @SerializedName("id")
    var id: Int,
//    @Expose
//    @SerializedName("original_language")
//    val original_language: String,
//    @Expose
//    @SerializedName("original_title")
//    val original_title: String,
    @Expose
    @SerializedName("overview")
    var overview: String,
//    @Expose
//    @SerializedName("popularity")
//    val popularity: Double,
    @Expose
    @SerializedName("poster_path")
    var poster_path: String,
    @Expose
    @SerializedName("release_date")
    var release_date: String,
    @Expose
    @SerializedName("title")
    var title: String,
//    @Expose
//    @SerializedName("video")
//    val video: Boolean,
    @Expose
    @SerializedName("vote_average")
    var vote_average: Double
//    @Expose
//    @SerializedName("vote_count")
//    val vote_count: Int

)

    :Parcelable {

    constructor():this(0,"","","","",0.0){}


    override fun toString(): String {
        return super.toString()
    }

     class BY_RELEASE_DATE : Comparator<Movie?> {
        override fun compare(o1: Movie?, o2: Movie?): Int {
            if (o1 == null || o2 == null) {
                return 0
            }
            return o1.release_date.compareTo(o2.release_date)
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    init {
        this.id = id
        this.overview = overview
        this.poster_path = poster_path
        this.title = title
        this.release_date = release_date
        this.vote_average = vote_average

    }
    init {

    }


}