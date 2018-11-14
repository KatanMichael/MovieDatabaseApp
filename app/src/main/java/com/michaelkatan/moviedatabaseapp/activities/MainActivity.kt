package com.michaelkatan.moviedatabaseapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.MenuItem
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.fragments.PopMovieMainFragment
import com.michaelkatan.moviedatabaseapp.fragments.PopPersonsMainFragment
import com.michaelkatan.moviedatabaseapp.fragments.PopTvShowsMainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
