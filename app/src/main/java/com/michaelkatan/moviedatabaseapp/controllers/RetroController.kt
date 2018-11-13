package com.michaelkatan.moviedatabaseapp.controllers

import android.util.Log
import com.michaelkatan.moviedatabaseapp.interfaces.DataRequest
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.MovieRequest
import com.michaelkatan.moviedatabaseapp.models.TvRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroController
{
    val API_KEY: String = "e59a3745b3e4534b3d9d3541be46ad9e"

    private val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val retroClient = retrofit.create(DataRequest::class.java)

    fun getMovies (name: String, requestListener: RequestListener, year: Int = 0, region : String = "")
    {
        retroClient.getMovies (API_KEY, name,region, year)
            .enqueue(object : Callback<MovieRequest>
            {
                override fun onFailure(call: Call<MovieRequest>, t: Throwable)
                {

                    if(t!= null)
                    {
                        requestListener.onError(t.message.toString())
                    }
                }

                override fun onResponse(call: Call<MovieRequest>, response: Response<MovieRequest>)
                {
                    Log.d("Retro","onResponse")

                    if(response != null)
                    {
                        val body = response.body()

                        if(body != null)
                        {
                            requestListener.onComplete(body.results)

                        }
                    }

                }

            })



    }

    fun getPopularMovies(requestListener: RequestListener, page: Int = 1)
    {
        Log.d("RetroController","getPopularMovies")

        retroClient.getPopularMovies(API_KEY,page = page)
                .enqueue(object : Callback<MovieRequest>
                {
                    override fun onFailure(call: Call<MovieRequest>, t: Throwable)
                    {
                        requestListener.onError(t.message.toString())
                        Log.d("RetroController","onFailure + ")

                    }

                    override fun onResponse(call: Call<MovieRequest>, response: Response<MovieRequest>)
                    {
                        Log.d("RetroController","onResponse")
                        Log.d("RetroController","Call: ${call.request().url()}")

                        if (response != null)
                        {
                            val body = response.body()

                            if(body!= null)
                            {
                                for(m in body.results)
                                {
                                    Log.d("RetroController","Movie: $m")
                                }

                                requestListener.onComplete(body.results)
                            }else
                            {
                                Log.d("RetroController","body is null: ${response.code()}")

                            }
                        }else
                        {
                            Log.d("RetroController","response is null")
                        }

                    }


                })
    }

    fun getPopularTvShows(requestListener: RequestListener, page: Int = 1)
    {
        retroClient.getPopularTvShows(apiKey = API_KEY, page = page)
            .enqueue(object : Callback<TvRequest>
            {
                override fun onFailure(call: Call<TvRequest>, t: Throwable)
                {
                    requestListener.onError(t.message.toString())
                }

                override fun onResponse(call: Call<TvRequest>, response: Response<TvRequest>)
                {
                    if (response != null)
                    {
                        val body = response.body()

                        if(body!= null)
                        {
                            for(m in body.results)
                            {
                                Log.d("RetroController","TvShow: $m")
                            }

                            requestListener.onComplete(body.results)
                        }else
                        {
                            Log.d("RetroController","body is null: ${response.code()}")

                        }
                    }else
                    {
                        Log.d("RetroController","response is null")
                    }

                }

            })
    }


}
