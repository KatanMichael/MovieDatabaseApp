package com.michaelkatan.moviedatabaseapp.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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
import com.michaelkatan.moviedatabaseapp.viewModels.TvShowViewModel
import kotlinx.android.synthetic.main.main_poptvshows_fragment.*

class PopTvShowsMainFragment : Fragment(),ItemClickListener
{


    val listofShows = ArrayList<PopularItem>()
    lateinit var tvShowViewModel: TvShowViewModel
    lateinit var popularTvShowAdapter : PopularAdapter<TvShow>

    var showPageCount = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecycleView(view)



    }

    private fun initRecycleView(view : View) {
        popularTvShowAdapter = PopularAdapter(tvShowViewModel.tvShowsList.value,view.context,this)

        main_fragemnt_pop_tvShows_recycle.adapter = popularTvShowAdapter

        main_fragemnt_pop_tvShows_recycle.layoutManager = GridLayoutManager(view.context,3)

        main_fragemnt_pop_tvShows_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int)
            {
                if (!recyclerView!!.canScrollVertically(1))
                {
                    showPageCount++
                    tvShowViewModel.getTvShowsByPage(showPageCount)

                    Log.d("MovieDBApp","onScrollStateChanged: ${showPageCount}")

                }
            }
        })
    }


    override fun onClickItem(v: View?, position: Int)
    {
        val bundle = Bundle()
        bundle.putInt("id",listofShows[position].popId)
        Log.d("itemFrag","Id: ${listofShows[position].popId}")
        bundle.putString("type","tvShow")

        val mainItemFragment = MainItemFragment()
        mainItemFragment.arguments = bundle

        val supportFragmentManager = this@PopTvShowsMainFragment.activity!!.supportFragmentManager

        supportFragmentManager.beginTransaction().replace(R.id.main_container,mainItemFragment,"itemFrag")
            .addToBackStack("itemFrag").commit()

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.main_poptvshows_fragment, container, false)
    }

    fun initViewModel()
    {
        tvShowViewModel = ViewModelProviders.of(this)
            .get(TvShowViewModel::class.java)

        tvShowViewModel.tvShowsList.observe(this, Observer { items ->

            if(items != null)
            {
                popularTvShowAdapter.notifyDataSetChanged()

            }


        })
    }
}