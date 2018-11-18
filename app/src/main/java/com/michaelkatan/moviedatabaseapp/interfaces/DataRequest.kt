package com.michaelkatan.moviedatabaseapp.interfaces

import com.michaelkatan.moviedatabaseapp.models.Movie
import com.michaelkatan.moviedatabaseapp.models.MovieRequest
import com.michaelkatan.moviedatabaseapp.models.PersonRequest
import com.michaelkatan.moviedatabaseapp.models.TvRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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


    @GET("person/popular")
    fun getPopularPersons(@Query("api_key") apiKey: String, @Query("language") language: String = "en-US"
                          ,@Query("page") page: Int = 1): retrofit2.Call<PersonRequest>

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") id:Int, @Query("api_key")apiKey: String): Call<Movie>
}