package com.fazlerabbikhan.cricfreak.adapter.matches

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.global.Constant
import com.fazlerabbikhan.cricfreak.model.fixtures.FixtureData
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class RecentAdapter(
    private val dataset: List<FixtureData>?,
) : RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormatter(date: String): String{
        val formatter = DateTimeFormatter.ISO_INSTANT
        val dateTime = LocalDateTime.parse(date, formatter.withZone(ZoneOffset.UTC))

        val formattedDate = dateTime.toLocalDate()

        return formattedDate.toString()
    }

    class RecentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stage: TextView = itemView.findViewById(R.id.recent_stage)
        val round: TextView = itemView.findViewById(R.id.recent_round)
        val date: TextView = itemView.findViewById(R.id.recent_date)
        val team1name: TextView = itemView.findViewById(R.id.recent_team1_name)
        val team2name: TextView = itemView.findViewById(R.id.recent_team2_name)
        val team1score: TextView = itemView.findViewById(R.id.recent_team1_score)
        val team2score: TextView = itemView.findViewById(R.id.recent_team2_score)
        val team1overs: TextView = itemView.findViewById(R.id.recent_team1_overs)
        val team2overs: TextView = itemView.findViewById(R.id.recent_team2_overs)
        val note: TextView = itemView.findViewById(R.id.recent_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_recent, parent, false)
        return RecentViewHolder(adapterLayout)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        val item = dataset?.get(position)

        if (item != null) {
            holder.stage.text = item.stage?.name
            holder.round.text = item.round
            holder.date.text = item.starting_at?.let { dateFormatter(it) }

            val team1runs = item.runs?.filter {
                it.inning == 1
            }
            val team2runs = item.runs?.filter {
                it.inning == 2
            }

            if(team1runs != null && team1runs.isNotEmpty()){
                Glide
                    .with(holder.itemView.context)
                    .load(team1runs[0].team?.image_path)
                    .fitCenter()
                    .into(holder.itemView.findViewById(R.id.recent_team1_image))
                holder.team1name.text = team1runs[0].team?.name
                if(team1runs[0].wickets != 10){
                    holder.team1score.text = "${team1runs[0].score}/${team1runs[0].wickets}"
                } else{
                    holder.team1score.text = "${team1runs[0].score}"
                }
                holder.team1overs.text = "(${team1runs[0].overs})"
            }

            if(team2runs != null && team2runs.isNotEmpty()){
                Glide
                    .with(holder.itemView.context)
                    .load(team2runs[0].team?.image_path)
                    .fitCenter()
                    .into(holder.itemView.findViewById(R.id.recent_team2_image))
                holder.team2name.text = team2runs[0].team?.name
                if(team2runs[0].wickets != 10){
                    holder.team2score.text = "${team2runs[0].score}/${team2runs[0].wickets}"
                } else{
                    holder.team2score.text = "${team2runs[0].score}"
                }
                holder.team2overs.text = "(${team2runs[0].overs})"
            }

            holder.note.text = item.note
        }

//        details fragment action
        holder.itemView.setOnClickListener {
            Constant.recentDetails = item
            it.findNavController().navigate(R.id.recentDetailsFragment)
        }
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}