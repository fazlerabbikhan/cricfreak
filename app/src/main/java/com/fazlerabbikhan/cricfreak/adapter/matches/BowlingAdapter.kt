package com.fazlerabbikhan.cricfreak.adapter.matches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.fixtures.Bowling

class BowlingAdapter(
    private val dataset: List<Bowling>?,
) : RecyclerView.Adapter<BowlingAdapter.BowlingViewHolder>() {

    class BowlingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.bowling_name)
        val overs: TextView = itemView.findViewById(R.id.bowling_overs)
        val maiden: TextView = itemView.findViewById(R.id.bowling_maiden)
        val runs: TextView = itemView.findViewById(R.id.bowling_runs)
        val wickets: TextView = itemView.findViewById(R.id.bowling_wickets)
        val economyRate: TextView = itemView.findViewById(R.id.bowling_economy_rate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BowlingViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_bowling, parent, false)
        return BowlingViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BowlingViewHolder, position: Int) {
        val item = dataset?.get(position)

        if(item != null){
            Glide
                .with(holder.itemView.context)
                .load(item.bowler?.image_path)
                .fitCenter()
                .into(holder.itemView.findViewById(R.id.bowling_image))
            holder.name.text = item.bowler?.fullname
            holder.overs.text = "${item.overs}"
            holder.maiden.text = "${item.medians}"
            holder.runs.text = "${item.runs}"
            holder.wickets.text = "${item.wickets}"
            holder.economyRate.text = "${item.rate}"
        }
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}