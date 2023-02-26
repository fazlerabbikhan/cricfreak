package com.fazlerabbikhan.cricfreak.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.players.PlayersData

class PlayersAdapter(
    private val dataset: List<PlayersData>?,
) : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.player_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_player, parent, false)
        return PlayersViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val item = dataset?.get(position)
        Glide
            .with(holder.itemView.context)
            .load(item?.image_path)
            .fitCenter()
            .into(holder.itemView.findViewById(R.id.player_image))
        holder.name.text = item?.lastname
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}