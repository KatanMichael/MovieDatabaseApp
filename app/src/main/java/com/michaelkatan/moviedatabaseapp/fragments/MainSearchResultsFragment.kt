package com.michaelkatan.moviedatabaseapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.adapters.SearchResultsAdapter
import com.michaelkatan.moviedatabaseapp.interfaces.ItemClickListener
import com.michaelkatan.moviedatabaseapp.interfaces.SendDataListener
import com.michaelkatan.moviedatabaseapp.models.UnknownItem
import kotlinx.android.synthetic.main.main_search_result_page.*

class MainSearchResultsFragment : Fragment(), ItemClickListener, SendDataListener {

    val itemsList = ArrayList<UnknownItem>()
    var adapter: SearchResultsAdapter? = null


    override fun <T> onDataReceived(data: T, type: String) {
        Log.d("SearchResults", "onDataReceived")

        val items = data as Array<UnknownItem>

        for (item in items) {
            Log.d("SearchResults", item.toString())
        }

        itemsList.addAll(items)
        adapter?.notifyDataSetChanged()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = SearchResultsAdapter(itemsList, view.context, this)

        main_search_recyclerView.adapter = adapter
        main_search_recyclerView.layoutManager = GridLayoutManager(view.context, 3)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_search_result_page, container, false)
    }


    override fun onClickItem(v: View?, position: Int) {
        val item = itemsList[position]

        val bundle = Bundle()

        bundle.putInt("id",item.id)

        val media_type = item.media_type

        if (media_type == "tv") {
            bundle.putString("type", "tvShow")
        }
        if (media_type == "movie") {
            bundle.putString("type", "movie")
        }

        val mainItemFrag = MainItemFragment()
        mainItemFrag.arguments = bundle

        val activity = this.activity

        if(activity != null)
        {
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.main_container,mainItemFrag,"itemFrag")
                .commitNow()
        }

    }

}