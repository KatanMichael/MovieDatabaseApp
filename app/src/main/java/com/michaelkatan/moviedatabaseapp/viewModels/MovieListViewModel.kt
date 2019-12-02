package com.michaelkatan.moviedatabaseapp.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.Movie

class MovieListViewModel: ViewModel()
{
    val retroController = RetroController

    var listOfPopMovies: MutableLiveData<ArrayList<Movie>> = MutableLiveData()

    init {

        retroController.getPopularMovies(object : RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                val tempList = ArrayList<Movie>()
                val res = results as Array<Movie>

                tempList.addAll(res)

                listOfPopMovies.postValue(tempList)
            }

            override fun onError(message: String)
            {
                //TODO Add Global Tag
            }

        })

    }


    fun getPopMoviesByPage(page: Int)
    {
        retroController.getPopularMovies(page = page,
            requestListener = object: RequestListener
            {
                override fun <T> onComplete(results: Array<T>)
                {
                    val tempList = ArrayList<Movie>()
                    val res = results as Array<Movie>

                    listOfPopMovies.value!!.addAll(res)

                }

                override fun onError(message: String) {

                }

            })
    }
}