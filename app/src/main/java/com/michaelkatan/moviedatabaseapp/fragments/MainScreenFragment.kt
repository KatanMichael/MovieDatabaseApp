package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.adapters.PopularAdapter
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.Movie
import com.michaelkatan.moviedatabaseapp.models.PopularItem
import kotlinx.android.synthetic.main.main_fragnent.*

class MainScreenFragment : Fragment(), View.OnClickListener
{

    val retroController: RetroController = RetroController
    val listofMovies = ArrayList<PopularItem>()


    override fun onClick(p0: View?)
    {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.main_fragnent, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val popularMoviesAdapter = PopularAdapter(listofMovies,this,view.context)

        main_fragemnt_pop_movies_recycle.adapter = popularMoviesAdapter

        main_fragemnt_pop_movies_recycle.layoutManager = GridLayoutManager(view.context,3)

        retroController.getPopularMovies(object :
                RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                val tempArray = results as Array<Movie>

                for(r in tempArray.indices)
                {

                    listofMovies.add(PopularItem(tempArray[r].id,tempArray[r].poster_path,"movie"))
                    Log.d("PopularMoviesFrag","Popular Item: $r.")

                }

                popularMoviesAdapter.notifyDataSetChanged()
            }

            override fun onError(message: String)
            {
                Log.d("PopularMoviesFrag","Error: $message")
            }

        })

//        retroController.getMovies("Matrix",object : RequestListener
//        {
//            override fun <T> onComplete(results: Array<T>)
//            {
//                val tempArray = results as Array<Movie>
//
//                for(t in tempArray)
//                {
//                    listofMovies.add(PopularItem(t.id,t.poster_path,"Movie"))
//
//                }
//
//                popularMoviesAdapter.notifyDataSetChanged()
//            }
//
//            override fun onError(message: String)
//            {
//
//            }
//
//
//        })

    }




}

//TODO figure out how to deal with the adapter