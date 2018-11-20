package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.interfaces.SendDataListener
import kotlinx.android.synthetic.main.main_item_info_page.*

class Main_Item_Info_Fragment: Fragment(), SendDataListener
{
    override fun <T> onDataReceived(data: T, type: String)
    {
        if(type == "overView")
        {
            val tempData = data as String
            main_item_info_overview.text = "Overview: $tempData"
        }

        if(type == "writers")
        {
            val tempData = data as String

            main_item_info_writers.text = "Writers: $tempData"
        }

        if(type == "director")
        {
            val tempData = data as String
            main_item_info_director.text = "Director: $tempData"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_item_info_page,container,false)
    }

}