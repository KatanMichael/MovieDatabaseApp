package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelkatan.moviedatabaseapp.R

class Main_Item_Info_Fragment: Fragment()
{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        Log.d("infoFrag","View Created")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_item_info_page,container,false)
    }

}