package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val popularMoviesAdapter = PopularAdapter(listOfMovies,view.context,this)

        main_fragemnt_pop_movies_recycle.adapter = popularMoviesAdapter

        main_fragemnt_pop_movies_recycle.layoutManager = GridLayoutManager(view.context, 3)

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

        listOfMovies.clear()



        for(i in 1..movieCount)
        {
            getMoviesByPageNumber(i,popularMoviesAdapter)
        }



    }

    override fun onClickItem(v: View?, position: Int)
    {
       val bundle = Bundle()
        bundle.putInt("id",listOfMovies[position].id)
        bundle.putString("type","movie")

        val mainItemFragment = MainItemFragment()
        mainItemFragment.arguments = bundle

        val supportFragmentManager = this@PopMovieMainFragment.activity!!.supportFragmentManager

        supportFragmentManager.beginTransaction().replace(R.id.main_container,mainItemFragment,"itemFrag")
            .addToBackStack("itemFrag").commit()

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

                    listOfMovies.add(PopularItem(tempArray[r].id,tempArray[r].poster_path,"movie"))


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

//TODO searchable interface