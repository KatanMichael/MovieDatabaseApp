package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.adapters.MainItemPageAdapter
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.CreditRequest
import com.michaelkatan.moviedatabaseapp.models.Movie
import com.michaelkatan.moviedatabaseapp.models.TvShow
import kotlinx.android.synthetic.main.main_item_screen.*

class MainItemFragment: Fragment()
{

    val subFragmentList = ArrayList<Fragment>()
    val fragmentTitleList = ArrayList<String>()


    val imagePrefix = "https://image.tmdb.org/t/p/w500/"
    val controller = RetroController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val type = arguments?.getString("type")
        val id = arguments?.getInt("id")


        val mainPagerAdapter =  MainItemPageAdapter(activity!!.supportFragmentManager,subFragmentList,fragmentTitleList)



        mainPagerAdapter.addFragment(Main_Item_Info_Fragment(),"info")
        mainPagerAdapter.addFragment(MainItemCastCrewFragment(),"cast")

        main_item_tabLayout.setupWithViewPager(main_item_pager)


        main_item_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?)
            {
                main_item_pager.currentItem = tab!!.position

            }

        })

        main_item_pager.adapter = mainPagerAdapter

        main_item_pager.currentItem = 0

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
                        val infoBundle = Bundle()

                        val overViewFrag = mainPagerAdapter.getItem(0) as Main_Item_Info_Fragment
                        overViewFrag.onDataReceived(item.overview,"overView")


                    }

                    override fun onError(message: String)
                    {
                        Log.d("MainItemFrag",message)
                    }

                })

                controller.getMovieCreditsById(object : RequestListener
                {
                    override fun <T> onComplete(results: Array<T>)
                    {
                        val item = results[0] as CreditRequest
                        var writers = ""
                        var diractors = ""
                        for(p in item.crew)
                        {
                            if(p.department == "Writing")
                            {
                                writers+=p.name+"| "
                            }

                            if(p.job == "Director")
                            {
                                diractors += p.name +" | "
                                Log.d("itemFrag",p.name)
                            }
                        }

                        val infoFrag = mainPagerAdapter.getItem(0) as Main_Item_Info_Fragment
                        infoFrag.onDataReceived(writers,"writers")
                        infoFrag.onDataReceived(diractors,"director")

                        val castFrag = mainPagerAdapter.getItem(1) as MainItemCastCrewFragment

                        castFrag.onDataReceived(item.cast,"cast")
                    }

                    override fun onError(message: String)
                    {
                        Log.d("ItemFrag",message)
                    }

                },id)

            }
        }

        if(type == "tvShow")
        {
            if(id != null)
            {
                controller.getTvShowById(object : RequestListener
                {
                    override fun onError(message: String)
                    {
                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                    }

                    override fun <T> onComplete(results: Array<T>)
                    {
                        val item = results[0] as TvShow
                        fillViewWithData(results[0],"tvShow")

                        val overViewFrag = mainPagerAdapter.getItem(0) as Main_Item_Info_Fragment
                        overViewFrag.onDataReceived(item.overview,"overView")

                    }

                },id)

                controller.getTvShowCreditsById(object : RequestListener
                {
                    override fun <T> onComplete(results: Array<T>)
                    {
                        val item = results[0] as CreditRequest
                        var writers = ""
                        var diractors = ""
                        for(p in item.crew)
                        {
                            if(p.department == "Writing")
                            {
                                writers+=p.name+"| "
                            }

                            if(p.job == "Director")
                            {
                                diractors += p.name +" | "
                                Log.d("itemFrag",p.name)
                            }
                        }

                        val infoFrag = mainPagerAdapter.getItem(0) as Main_Item_Info_Fragment
                        infoFrag.onDataReceived(writers,"writers")
                        infoFrag.onDataReceived(diractors,"director")

                        val castFrag = mainPagerAdapter.getItem(1) as MainItemCastCrewFragment
                        castFrag.onDataReceived(item.cast,"cast")
                    }

                    override fun onError(message: String)
                    {
                        Log.d("MainItem",message)
                    }

                },id)
            }
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_item_screen,container,false)
    }

    fun <T> fillViewWithData(item: T, type:String)
    {
        if(type == "movie")
        {
            val item = item as Movie

            Glide.with(this@MainItemFragment).load(imagePrefix+item.backdrop_path).into(main_item_backdrop)
            Glide.with(this@MainItemFragment).load(imagePrefix+item.poster_path).into(main_item_poster)
            main_item_title.text = item.title
            main_item_releaseDate.text = item.release_date
            var genre : String = ""

            for(s in item.genres)
            {
                genre+= " | ${s.name}"
            }

            main_item_genre.text = genre+" |"
            return
        }

        if(type == "tvShow")
        {
            val item = item as TvShow

            Glide.with(this@MainItemFragment).load(imagePrefix+item.backdrop_path).into(main_item_backdrop)
            Glide.with(this@MainItemFragment).load(imagePrefix+item.poster_path).into(main_item_poster)
            main_item_title.text = item.original_name
            main_item_releaseDate.text = item.first_air_date
            var genre : String = ""

            for(s in item.genres)
            {
                genre+= " | ${s.name}"
            }

            main_item_genre.text = genre+" |"

        }


    }
}