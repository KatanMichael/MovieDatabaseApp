package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.Person
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.Movie
import com.michaelkatan.moviedatabaseapp.models.TvShow
import kotlinx.android.synthetic.main.main_item_screen.*

class Main_Item_Fragment: Fragment()
{

    val imagePrefix = "https://image.tmdb.org/t/p/w500/"
    val controller = RetroController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val type = arguments?.getString("type")
        val id = arguments?.getInt("id")

        if(type == "movie")
        {
            if (id != null)
            {
                controller.getMovieById(id = id, requestListener = object : RequestListener
                {
                    override fun <T> onComplete(results: Array<T>)
                    {
                        val item = results[0] as Movie

                        fillViewWithData(item,"movie")

                    }

                    override fun onError(message: String)
                    {
                        Log.d("MainItemFrag",message)
                    }

                })
            }
        }

        activity?.actionBar?.hide()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_item_screen,container,false)
    }

    fun <T> fillViewWithData(item: T, type:String)
    {
        if(type == "movie")
        {
            val item = item as Movie

            Glide.with(this@Main_Item_Fragment).load(imagePrefix+item.backdrop_path).into(main_item_backdrop)
            Glide.with(this@Main_Item_Fragment).load(imagePrefix+item.poster_path).into(main_item_poster)

        }
    }
}