package com.michaelkatan.moviedatabaseapp.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.interfaces.ItemClickListener
import com.michaelkatan.moviedatabaseapp.models.UnknownItem

class SearchResultsAdapter(var items: ArrayList<UnknownItem>, val context: Context,
                           val myClickListener: ItemClickListener):
    RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_item,parent,false)

        return SearchResultViewHolder(view)

    }

    override fun getItemCount(): Int
    {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int)
    {
        val item = items[position]

        val imagePrefix = "https://image.tmdb.org/t/p/w500/"
        val urlString = (imagePrefix+items!!.get(position).poster_path)

        Glide.with(context)
            .load(urlString)
            .into(holder.itemPosterImage)

        holder.itemPosterImage.setOnClickListener()
        {
            myClickListener.onClickItem(holder.view,position)
        }

    }




    class SearchResultViewHolder(val view: View): RecyclerView.ViewHolder(view)
    {
        val itemPosterImage : ImageView = view.findViewById(R.id.popular_item_poster)
    }


}