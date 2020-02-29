package com.michaelkatan.moviedatabaseapp.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.TvShow

class TvShowViewModel : ViewModel() {
    var tvShowsList: MutableLiveData<ArrayList<TvShow>> = MutableLiveData()
    var retroController = RetroController

    init {

        retroController.getPopularTvShows(object : RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                if(results != null)
                {
                    val res = results as Array<TvShow>
                    val tempList = ArrayList<TvShow>()
                    tempList.addAll(res)
                    tvShowsList.postValue(tempList)
                }
            }

            override fun onError(message: String) {

            }

        })
    }

    fun getTvShowsByPage(page: Int) {
        retroController.getPopularTvShows(page = page,
            requestListener = object : RequestListener {
                override fun <T> onComplete(results: Array<T>)
                {
                    if(results != null)
                    {
                        val res = results as Array<TvShow>
                        val tempList = ArrayList<TvShow>()
                        tempList.addAll(res)
                        tvShowsList.postValue(tempList)
                    }
                }

                override fun onError(message: String) {

                }

            })
    }
}