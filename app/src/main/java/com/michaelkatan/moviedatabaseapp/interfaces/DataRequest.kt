package com.michaelkatan.moviedatabaseapp.interfaces

import com.michaelkatan.moviedatabaseapp.models.MovieRequest
import com.michaelkatan.moviedatabaseapp.models.TvRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface DataRequest
{


    @GET("search/movie")
    fun getMovies(@Query("api_key") apiKey: String, @Query("query") query : String,
                  @Query("region")region: String = "", @Query("year") year: Int = 0): retrofit2.Call<MovieRequest>


    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("language") language: String = "en-US",
                         @Query("page") page: Int = 1, @Query("region") region: String = " "): retrofit2.Call<MovieRequest>


    @GET("tv/popular")
    fun getPopularTvShows(@Query("api_key") apiKey: String, @Query("language") language: String = "en-US",
                          @Query("page") page: Int = 1): retrofit2.Call<TvRequest>
}