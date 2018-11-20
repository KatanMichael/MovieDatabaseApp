package com.michaelkatan.moviedatabaseapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.models.Cast
import de.hdodenhof.circleimageview.CircleImageView

class MainItemCastAdapter(val item: ArrayList<Cast>) : RecyclerView.Adapter<MainItemCastAdapter.CastAndCrewViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAndCrewViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item_cast_item,parent,false)

        return CastAndCrewViewHolder(view)
    }

    override fun getItemCount(): Int
        {
            return item.count()
        }

        override fun onBindViewHolder(holder: CastAndCrewViewHolder, position: Int)
        {


    }


    class CastAndCrewViewHolder(val view: View): RecyclerView.ViewHolder(view)
    {
        val profileImage = view.findViewById(R.id.main_item_cast_profileImage) as CircleImageView

        val name = view.findViewById(R.id.main_item_cast_name) as TextView

        val job = view.findViewById(R.id.main_item_cast_job) as TextView
    }
}