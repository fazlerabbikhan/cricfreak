package com.fazlerabbikhan.cricfreak.adapter.matches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.fixtures.Lineup

class LineupAdapter(
    private val dataset: List<Lineup>?,
) : RecyclerView.Adapter<LineupAdapter.LineupViewHolder>() {

    class LineupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.lineup_name)
        val captain: TextView = itemView.findViewById(R.id.lineup_captain)
        val position: TextView = itemView.findViewById(R.id.lineup_position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineupViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lineup, parent, false)
        return LineupViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: LineupViewHolder, position: Int) {
        val item = dataset?.get(position)

        if(item != null){
            Glide
                .with(holder.itemView.context)
                .load(item.image_path)
                .fitCenter()
                .into(holder.itemView.findViewById(R.id.lineup_image))
            holder.name.text = item.fullname
            if(item.lineup?.captain == false){
                holder.captain.text = ""
            }
            holder.position.text = item.position?.name
        }
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}