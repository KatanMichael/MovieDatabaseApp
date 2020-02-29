package com.michaelkatan.moviedatabaseapp.controllers

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.michaelkatan.moviedatabaseapp.interfaces.DataRequest
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Callable

object RetroController
{
    private val API_KEY: String = "e59a3745b3e4534b3d9d3541be46ad9e"

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

                        if (response != null)
                        {
                            val body = response.body()

                            if(body!= null)
                            {

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
                            requestListener.onComplete(body.results)
                            

                        }else
                        {
                            Log.d("MovieDBApp","body is null: ${response.code()}")

                        }
                    }else
                    {
                        Log.d("MovieDBApp","response is null")
                    }

                }

            })
    }

    fun testGetPopularTvShows(page: Int = 1) : MutableLiveData<>
    {

    }

    fun getPopularPersons(requestListener: RequestListener, page: Int = 1)
    {
        retroClient.getPopularPersons(API_KEY,page = page)
            .enqueue(object : Callback<PersonRequest>
            {
                override fun onFailure(call: Call<PersonRequest>, t: Throwable)
                {
                    requestListener.onError(t.message.toString())
                }

                override fun onResponse(call: Call<PersonRequest>, response: Response<PersonRequest>)
                {
                    if(response != null)
                    {
                            val body = response.body()
                            if(body != null)
                            {
                                requestListener.onComplete(body.results)
                            }
                    }
                }

            }

            )


    }

    fun getMovieById(requestListener: RequestListener, id: Int)
    {
        retroClient.getMovieById(id,API_KEY)
            .enqueue(object : Callback<Movie>
            {
                override fun onFailure(call: Call<Movie>, t: Throwable)
                {
                    requestListener.onError(t.message.toString())
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>)
                {
                    val tempArray = ArrayList<Movie>()

                    if(response != null)
                    {
                        val body = response.body()

                        if(body != null)
                        {
                            tempArray.add(body)
                            requestListener.onComplete(tempArray.toArray())
                        }
                    }
                }

            })
    }

    fun getTvShowById(requestListener: RequestListener, id: Int)
    {
        retroClient.getTvShowById(id, API_KEY).enqueue(object :Callback<TvShow>
        {
            override fun onFailure(call: Call<TvShow>, t: Throwable)
            {
                requestListener.onError(t.message.toString())
            }

            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>)
            {
                if(response != null)
                {
                    val body = response.body()

                    if(body != null)
                    {
                        val tempArray = ArrayList<TvShow>()
                        tempArray.add(body)

                        requestListener.onComplete(tempArray.toArray())
                    }
                }
            }

        }
        )
    }

    fun getMovieCreditsById(requestListener: RequestListener, id: Int)
    {
        retroClient.getMovieCreditsById(id, API_KEY)
            .enqueue(object :Callback<CreditRequest>
            {
                override fun onFailure(call: Call<CreditRequest>, t: Throwable)
                {
                    requestListener.onError(t.message.toString())
                }

                override fun onResponse(call: Call<CreditRequest>, response: Response<CreditRequest>)
                {
                    val tempArray = ArrayList<CreditRequest>()

                    if(response != null)
                    {
                        val body = response.body()

                        if(body != null)
                        {
                            tempArray.add(body)
                            requestListener.onComplete(tempArray.toArray())
                        }
                    }
                }

            })
    }

    fun getTvShowCreditsById(requestListener: RequestListener, id: Int)
    {
        retroClient.getTvShowCreditsById(id, API_KEY)
            .enqueue(object :Callback<CreditRequest>
            {
                override fun onFailure(call: Call<CreditRequest>, t: Throwable)
                {
                    requestListener.onError(t.message.toString())
                }

                override fun onResponse(call: Call<CreditRequest>, response: Response<CreditRequest>)
                {
                    if(response != null)
                    {
                        val body = response.body()
                        if(body != null)
                        {
                            val tempArr = Array<CreditRequest>(1){body}
                            requestListener.onComplete(tempArr)
                        }
                    }
                }

            })
    }

    fun getMultiSearchResults(requestListener: RequestListener, query: String)
    {
        retroClient.getMultiSearchResuls(API_KEY, query)
            .enqueue(object :Callback<MultiSearchResult>
            {
                override fun onFailure(call: Call<MultiSearchResult>, t: Throwable)
                {
                    requestListener.onError(t.message.toString())
                }

                override fun onResponse(call: Call<MultiSearchResult>, response: Response<MultiSearchResult>)
                {
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
}
