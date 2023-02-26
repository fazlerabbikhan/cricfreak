package com.fazlerabbikhan.cricfreak.adapter.matches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.fixtures.Batting

class BattingAdapter(
    private val dataset: List<Batting>?,
) : RecyclerView.Adapter<BattingAdapter.BattingViewHolder>() {

    class BattingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.batting_name)
        val runs: TextView = itemView.findViewById(R.id.batting_runs)
        val balls: TextView = itemView.findViewById(R.id.batting_balls)
        val fours: TextView = itemView.findViewById(R.id.batting_fours)
        val sixes: TextView = itemView.findViewById(R.id.batting_sixes)
        val strikeRate: TextView = itemView.findViewById(R.id.batting_strike_rate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattingViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_batting, parent, false)
        return BattingViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BattingViewHolder, position: Int) {
        val item = dataset?.get(position)

        if(item != null){
            Glide
                .with(holder.itemView.context)
                .load(item.batsman?.image_path)
                .fitCenter()
                .into(holder.itemView.findViewById(R.id.batting_image))
            holder.name.text = item.batsman?.fullname
            holder.runs.text = "${item.score}"
            holder.balls.text = "${item.ball}"
            holder.fours.text = "${item.four_x}"
            holder.sixes.text = "${item.six_x}"
            holder.strikeRate.text = "${item.rate}"
        }
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}