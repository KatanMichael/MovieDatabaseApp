package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val retroController: RetroController = RetroController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.main_fragnent, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {



        retroController.getPopularMovies(object :
                RequestListener {
            override fun <T> onComplete(results: Array<T>)
            {
                val tempArray = ArrayList<PopularItem>(results.size)

                for(r in results.indices)
                {
                    tempArray.get(r).convertToPopularItem(results.get(r) as Movie)
                }

                main_fragemnt_pop_movies_recycle.layoutManager = LinearLayoutManager(this@MainScreenFragment.context!!)
                main_fragemnt_pop_movies_recycle.adapter = PopularAdapter(tempArray.toArray() as Array<PopularItem>?,this@MainScreenFragment, this@MainScreenFragment.context!!)

            }

            override fun onError(message: String)
            {

            }

        })

    }




}

//TODO figure out how to deal with the adapter