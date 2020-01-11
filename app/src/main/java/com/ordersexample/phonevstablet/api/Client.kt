package com.ordersexample.mytabkotlinapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    const val  BASE_URL:String = "http://api.themoviedb.org/3/"

    var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}