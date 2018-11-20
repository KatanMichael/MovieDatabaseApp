package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.adapters.MainItemCastAdapter
import com.michaelkatan.moviedatabaseapp.interfaces.SendDataListener
import com.michaelkatan.moviedatabaseapp.models.Cast
import kotlinx.android.synthetic.main.main_item_cast_page.*

class MainItemCastCrewFragment: Fragment(), SendDataListener
{
    val castList = ArrayList<Cast>()
    var adapter : MainItemCastAdapter? = null

    override fun <T> onDataReceived(data: T, type: String)
    {
        if(type == "cast")
        {
            val tempData = data as Array<Cast>

            castList.addAll(tempData)
            adapter?.notifyDataSetChanged()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        adapter = MainItemCastAdapter(castList,view.context)

        main_item_cast_recycleView.adapter = adapter
        main_item_cast_recycleView.layoutManager = LinearLayoutManager(context)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.main_item_cast_page,container,false)
    }
}