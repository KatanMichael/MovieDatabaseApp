package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.adapters.PopularAdapter
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.ItemClickListener
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.PopularItem
import com.michaelkatan.moviedatabaseapp.models.TvShow
import kotlinx.android.synthetic.main.main_poptvshows_fragment.*

class PopTvShowsMainFragment : Fragment(),ItemClickListener
{


    val retroController: RetroController = RetroController
    val listofShows = ArrayList<PopularItem>()

    var showPageCount = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val popularTvShowAdapter = PopularAdapter(listofShows, view.context,this)

        main_fragemnt_pop_tvShows_recycle.adapter = popularTvShowAdapter

        main_fragemnt_pop_tvShows_recycle.layoutManager = GridLayoutManager(view.context,3)

        getShowsByPage(showPageCount, popularTvShowAdapter)

        main_fragemnt_pop_tvShows_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int)
            {
                if (!recyclerView!!.canScrollVertically(1))
                {
                    showPageCount++
                    getShowsByPage(showPageCount, popularTvShowAdapter)
                }
            }
        })

    }

    fun getShowsByPage(requsetPage: Int, adapter: PopularAdapter )
    {
        retroController.getPopularTvShows(object : RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                val tempArray = results as Array<TvShow>

                for(show in tempArray)
                {
                    listofShows.add(PopularItem(show.id, show.poster_path,"tvShow"))
                }

                adapter.notifyDataSetChanged()
            }

            override fun onError(message: String)
            {
                Toast.makeText(context,"Error: $message",Toast.LENGTH_SHORT).show()
            }

        }, page = requsetPage)
    }



    override fun onClickItem(v: View?, position: Int)
    {

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.main_poptvshows_fragment, container, false)
    }
}