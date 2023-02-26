package com.fazlerabbikhan.cricfreak.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.teams.TeamData

class ExploreAdapter(
    private val dataset: List<TeamData>,
) : RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {

    class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.team_name)
        val code: TextView = itemView.findViewById(R.id.team_code)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_team, parent, false)
        return ExploreViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val item = dataset.get(position)

        Glide
            .with(holder.itemView.context)
            .load(item.image_path)
            .fitCenter()
            .into(holder.itemView.findViewById(R.id.team_image))
        holder.name.text = item.name
        holder.code.text = item.code
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}