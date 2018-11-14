package com.michaelkatan.moviedatabaseapp.fragments

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
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import com.michaelkatan.moviedatabaseapp.models.Person
import com.michaelkatan.moviedatabaseapp.models.PopularItem
import kotlinx.android.synthetic.main.main_fragnent.*
import kotlinx.android.synthetic.main.main_pop_persons_fragment.*

class PopPersonsMainFragment : Fragment(), View.OnClickListener
{
    val retroController: RetroController = RetroController
    val listofPersons = ArrayList<PopularItem>()
    var personCount = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {

        val popularPersonsAdapter = PopularAdapter(listofPersons,this,view.context)
        main_fragemnt_pop_persons_recycle.adapter = popularPersonsAdapter
        main_fragemnt_pop_persons_recycle.layoutManager = GridLayoutManager(view.context,3)

        getPersonsByPage(1,popularPersonsAdapter)

        main_fragemnt_pop_persons_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int)
            {
                if (!recyclerView!!.canScrollVertically(1))
                {
                    personCount++
                    getPersonsByPage(personCount, popularPersonsAdapter)
                }
            }

        })

    }

    fun getPersonsByPage(page: Int, adapter: PopularAdapter)
    {
        retroController.getPopularPersons(page = page,requestListener = object : RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                val tempArray = results as Array<Person>

                for(p in tempArray)
                {
                    listofPersons.add(PopularItem(p.id,p.profile_path,"person"))
                }

                adapter.notifyDataSetChanged()

            }

            override fun onError(message: String)
            {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                Log.d("PersonsFrag",message)

            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_pop_persons_fragment, container,false)
    }


    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}