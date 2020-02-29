package com.michaelkatan.moviedatabaseapp.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.Cast

class ActorViewModel: ViewModel()
{
    val retroController = RetroController

    var actorViewModel = MutableLiveData<ArrayList<Cast>>()

    init {
        retroController.getPopularPersons(object: RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                if(results != null)
                {
                    val res = results as Array<Cast>
                    val tempList = ArrayList<Cast>()
                    tempList.addAll(res)

                    actorViewModel.postValue(tempList)
                }
            }

            override fun onError(message: String)
            {

            }

        })

    }

}