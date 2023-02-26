package com.fazlerabbikhan.cricfreak.adapter.rankings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.rankings.Team

class TestAdapter(
    private val dataset: List<Team>?,
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val position: TextView = itemView.findViewById(R.id.rankings_position)
        val name: TextView = itemView.findViewById(R.id.rankings_name)
        val matchesPlayed: TextView = itemView.findViewById(R.id.rankings_matches_played)
        val points: TextView = itemView.findViewById(R.id.rankings_points)
        val ratings: TextView = itemView.findViewById(R.id.rankings_ratings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_rankings, parent, false)
        return TestViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val item = dataset?.get(position)
        holder.position.text = "${item?.position}"
        Glide
            .with(holder.itemView.context)
            .load(item?.image_path)
            .fitCenter()
            .into(holder.itemView.findViewById(R.id.rankings_image))
        holder.name.text = item?.name
        holder.matchesPlayed.text = "${item?.ranking?.matches}"
        holder.points.text = "${item?.ranking?.points}"
        holder.ratings.text = "${item?.ranking?.rating}"
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}