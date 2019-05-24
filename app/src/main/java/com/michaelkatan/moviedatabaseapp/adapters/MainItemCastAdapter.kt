package com.michaelkatan.moviedatabaseapp.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.models.Cast
import de.hdodenhof.circleimageview.CircleImageView

class MainItemCastAdapter(val items: ArrayList<Cast>, val context: Context) : RecyclerView.Adapter<MainItemCastAdapter.CastAndCrewViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAndCrewViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item_cast_item,parent,false)

        return CastAndCrewViewHolder(view)
    }

    override fun getItemCount(): Int
        {
            return items.count()
        }

        override fun onBindViewHolder(holder: CastAndCrewViewHolder, position: Int)
        {

            val imagePrefix = "https://image.tmdb.org/t/p/w500/"
            val urlString = (imagePrefix+items.get(position).profile_path)

            if(items[position].profile_path == null)
            {
               Glide.with(context)
                   .load(R.drawable.ic_person_black)
                   .into(holder.profileImage)
            }else
            {
                Glide.with(context)
                    .load(urlString)
                    .into(holder.profileImage)
            }

            holder.name.text = items[position].name

            holder.job.text = items[position].character


        }


    class CastAndCrewViewHolder(val view: View): RecyclerView.ViewHolder(view)
    {
        val profileImage = view.findViewById(R.id.main_item_cast_profileImage) as CircleImageView

        val name = view.findViewById(R.id.main_item_cast_name) as TextView

        val job = view.findViewById(R.id.main_item_cast_job) as TextView
    }
}