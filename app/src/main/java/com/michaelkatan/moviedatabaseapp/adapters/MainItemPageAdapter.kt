package com.michaelkatan.moviedatabaseapp.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter

class MainItemPageAdapter(val fm: FragmentManager?,
                          val fragmentList: ArrayList<Fragment>,
                          val titleList: ArrayList<String>) : FragmentStatePagerAdapter(fm)
{


    override fun getItem(position: Int): Fragment
    {
        return fragmentList[position]
    }

    override fun getCount(): Int
    {
        return fragmentList.size
    }

    fun addFragment(frag:Fragment,title:String)
    {
        fragmentList.add(frag)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}