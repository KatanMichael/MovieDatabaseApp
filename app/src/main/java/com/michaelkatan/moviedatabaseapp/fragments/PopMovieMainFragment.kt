package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
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

class PopMovieMainFragment : Fragment(), View.OnClickListener
{

    val retroController: RetroController = RetroController
    val listofMovies = ArrayList<PopularItem>()
    var movieCount = 1

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

        getMoviesByPageNumber(movieCount,popularMoviesAdapter)

        main_fragemnt_pop_movies_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int)
            {
                if (!recyclerView!!.canScrollVertically(1))
                {
                    movieCount++
                    getMoviesByPageNumber(movieCount, popularMoviesAdapter)
                }
            }

        })
    }


    fun getMoviesByPageNumber(pageNumber: Int, adapter: PopularAdapter)
    {
        retroController.getPopularMovies(object :
            RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                val tempArray = results as Array<Movie>

                for(r in tempArray.indices)
                {

                    listofMovies.add(PopularItem(tempArray[r].id,tempArray[r].poster_path,"movie"))


                }

                adapter.notifyDataSetChanged()
            }

            override fun onError(message: String)
            {
                Log.d("PopularMoviesFrag","Error: $message")
            }

        },page = pageNumber)
    }

}

