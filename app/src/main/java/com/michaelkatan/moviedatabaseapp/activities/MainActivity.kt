package com.michaelkatan.moviedatabaseapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.util.Log
import android.view.MenuItem
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.fragments.MainSearchResultsFragment
import com.michaelkatan.moviedatabaseapp.fragments.PopMovieMainFragment
import com.michaelkatan.moviedatabaseapp.fragments.PopPersonsMainFragment
import com.michaelkatan.moviedatabaseapp.fragments.PopTvShowsMainFragment
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.UnknownItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    val controller = RetroController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(resources.getDrawable(R.drawable.ic_menu))


        toolBarSearch_Btn.setOnClickListener()
        {
            val searchQuery = toolBarSearch_Et.text.toString()
            controller.getMultiSearchResults(object : RequestListener
            {
                override fun <T> onComplete(results: Array<T>)
                {
                    val tempArr = results as Array<UnknownItem>

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container,MainSearchResultsFragment(),"searchResult")
                        .commitNow()

                    val searchResultFrag =
                        supportFragmentManager.findFragmentByTag("searchResult") as MainSearchResultsFragment?

                    if(searchResultFrag != null)
                    {
                        searchResultFrag.onDataReceived(tempArr,"")
                        Log.d("SearchResults","data sent")
                    }else
                    {
                        Log.d("SearchResults","searchResultFrag is null")
                    }



                }

                override fun onError(message: String)
                {
                    Log.d("MainActivity", message)
                }

            },searchQuery)
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container,PopMovieMainFragment(),"Movie").commit()

        bottomNavigation.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemReselectedListener,
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean
            {
                if(item.itemId == R.id.navigation_Movies)
                {
                    val findFragmentByTag = supportFragmentManager.findFragmentByTag("Movie")

                    if(!(findFragmentByTag != null && findFragmentByTag.isVisible))
                    {
                        supportFragmentManager.beginTransaction().replace(R.id.main_container,PopMovieMainFragment(),"Movie").commit()
                    }

                    return true
                }

                if(item.itemId == R.id.navigation_tvShows)
                {
                    val findFragmentByTag = supportFragmentManager.findFragmentByTag("TvShow")

                    if(!(findFragmentByTag != null && findFragmentByTag.isVisible))
                    {
                        supportFragmentManager.beginTransaction().replace(R.id.main_container,PopTvShowsMainFragment(),"TvShow").commit()
                    }
                    return true
                }
                if(item.itemId == R.id.navigation_people)
                {
                    val findFragmentByTag = supportFragmentManager.findFragmentByTag("Person")

                    if(!(findFragmentByTag != null && findFragmentByTag.isVisible))
                    {
                        supportFragmentManager.beginTransaction().replace(R.id.main_container,PopPersonsMainFragment(),"Person").commit()
                    }
                    return true
                }

                return false
            }

            override fun onNavigationItemReselected(item: MenuItem)
            {


            }


        })

    }
}
