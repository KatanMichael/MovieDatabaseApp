package com.michaelkatan.moviedatabaseapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.interfaces.ItemClickListener
import com.michaelkatan.moviedatabaseapp.models.PopularItem

class PopularAdapter(var items: ArrayList<PopularItem>?
                     , val context: Context, val myClickListener: ItemClickListener) : RecyclerView.Adapter<PopularAdapter.PopularResultViewHolder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.PopularResultViewHolder
    {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_item,parent,false)

        return PopularResultViewHolder(view)
    }



    override fun getItemCount(): Int
    {
        if(items != null)
        {
            return items!!.size
        }else
        {
            return 0
        }
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularResultViewHolder, position: Int)
    {

        val imagePrefix = "https://image.tmdb.org/t/p/w500/"
        val urlString = (imagePrefix+items!!.get(position).PosterPath)

        Glide.with(context)
                .load(urlString)
                .into(holder.moviePosterImage)


        holder.moviePosterImage.setOnClickListener()
        {
            myClickListener.onClickItem(holder.view,position)
        }
    }


    class PopularResultViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        val moviePosterImage : ImageView = view.findViewById(R.id.popular_item_poster)

    }



}