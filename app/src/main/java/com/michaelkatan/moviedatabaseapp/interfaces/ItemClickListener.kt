package com.michaelkatan.moviedatabaseapp.interfaces

import android.view.View

interface ItemClickListener
{
    fun onClickItem(v: View?, position: Int)
}