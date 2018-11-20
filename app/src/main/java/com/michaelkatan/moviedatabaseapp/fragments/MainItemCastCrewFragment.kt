package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.interfaces.SendDataListener

class MainItemCastCrewFragment: Fragment(), SendDataListener
{
    override fun <T> onDataReceived(data: T, type: String)
    {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.main_item_cast_page,container,false)
    }
}