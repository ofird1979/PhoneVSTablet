package com.ordersexample.mytabkotlinapp.services

import com.ordersexample.mytabkotlinapp.model.MoviesResponse
import com.ordersexample.mytabkotlinapp.model.TrailersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("search/movie")
    fun getMovieByName(
        @Query("api_key") apiKey: String?, @Query(
            "query"
        ) subject: String?
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(@Path("movie_id") id: Int, @Query("api_key") apiKey: String?): Call<TrailersResponse>


//    companion object{
//        fun create() :Service{
//            val retrofit=Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl("http://api.themoviedb.org/3/").build()
//            return retrofit.create(Service::class.java)
//        }
//    }

}