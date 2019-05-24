package com.michaelkatan.moviedatabaseapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class MainItemPageAdapter(val fm: FragmentManager,
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

    fun addFragment(frag: Fragment, title:String)
    {
        fragmentList.add(frag)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}