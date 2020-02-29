package com.michaelkatan.moviedatabaseapp.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.adapters.PopularAdapter
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.ItemClickListener
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.Movie
import com.michaelkatan.moviedatabaseapp.models.PopularItem
import com.michaelkatan.moviedatabaseapp.viewModels.MovieListViewModel
import kotlinx.android.synthetic.main.main_fragnent.*

class PopMovieMainFragment : Fragment(), ItemClickListener
{


    private val  retroController: RetroController = RetroController
    val listOfMovies = ArrayList<PopularItem>()
    var movieCount = 1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.main_fragnent, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {

        val popMoviesViewModel = ViewModelProviders.of(this)
            .get(MovieListViewModel::class.java).listOfPopMovies

        val popularMoviesAdapter = PopularAdapter(listOfMovies,view.context,this)

        main_fragemnt_pop_movies_recycle.adapter = popularMoviesAdapter

        main_fragemnt_pop_movies_recycle.layoutManager = LinearLayoutManager(context)



        popMoviesViewModel.observe(this, Observer {

            if(it != null)
            {
                val tempList = ArrayList<PopularItem>()
                tempList.addAll(listOfMovies)
                for(movie in it)
                {
                    tempList.add(PopularItem(movie))
                }

                listOfMovies.clear()
                listOfMovies.addAll(tempList)

                popularMoviesAdapter.notifyDataSetChanged()
            }

        })

//        main_fragemnt_pop_movies_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener()
//        {
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int)
//            {
//                if (!recyclerView!!.canScrollVertically(1))
//                {
//                    movieCount++
//                    getMoviesByPageNumber(movieCount, popularMoviesAdapter)
//                }
//            }
//
//        })
//
//        listOfMovies.clear()
//
//
//
//        for(i in 1..movieCount)
//        {
//            getMoviesByPageNumber(i,popularMoviesAdapter)
//        }
//


    }

    override fun onClickItem(v: View?, position: Int)
    {
        val bundle = Bundle()
        bundle.putInt("id",listOfMovies[position].popId)
        bundle.putString("type","movie")

        val mainItemFragment = MainItemFragment()
        mainItemFragment.arguments = bundle

        val supportFragmentManager = this@PopMovieMainFragment.activity!!.supportFragmentManager

        supportFragmentManager.beginTransaction().replace(R.id.main_container,mainItemFragment,"itemFrag")
            .addToBackStack("itemFrag").commit()

    }


}

//TODO searchable interface