package com.fazlerabbikhan.cricfreak.adapter.matches

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.fixtures.FixtureData
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class UpcomingAdapter(
    private val dataset: List<FixtureData>?
) : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormatter(date: String): String{
        val formatter = DateTimeFormatter.ISO_INSTANT
        val dateTime = LocalDateTime.parse(date, formatter.withZone(ZoneOffset.UTC))

        val formattedDate = dateTime.toLocalDate()

        return formattedDate.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun timeFormatter(time: String): String{
        val formatter = DateTimeFormatter.ISO_INSTANT
        val dateTime = LocalDateTime.parse(time, formatter.withZone(ZoneOffset.UTC))

        val formattedTime = dateTime.toLocalTime()

        return formattedTime.toString()
    }

    class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stage: TextView = itemView.findViewById(R.id.upcoming_stage)
        val round: TextView = itemView.findViewById(R.id.upcoming_round)
        val date: TextView = itemView.findViewById(R.id.upcoming_date)
        val time: TextView = itemView.findViewById(R.id.upcoming_time)
        val team1name: TextView = itemView.findViewById(R.id.upcoming_team1_name)
        val team2name: TextView = itemView.findViewById(R.id.upcoming_team2_name)
        val venueName: TextView = itemView.findViewById(R.id.upcoming_venue_name)
        val venueCity: TextView = itemView.findViewById(R.id.upcoming_venue_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_upcoming, parent, false)
        return UpcomingViewHolder(adapterLayout)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val item = dataset?.get(position)
        if (item != null) {
            holder.stage.text = item.stage?.name
            holder.round.text = item.round
            holder.date.text = item.starting_at?.let { dateFormatter(it) }
            holder.time.text = item.starting_at?.let { timeFormatter(it) }
            holder.team1name.text = item.localteam?.name
            holder.team2name.text = item.visitorteam?.name
            holder.venueName.text = item.venue?.name
            holder.venueCity.text = item.venue?.city

            Glide
                .with(holder.itemView.context)
                .load(item.localteam?.image_path)
                .fitCenter()
                .into(holder.itemView.findViewById(R.id.upcoming_team1_image))

            Glide
                .with(holder.itemView.context)
                .load(item.visitorteam?.image_path)
                .fitCenter()
                .into(holder.itemView.findViewById(R.id.upcoming_team2_image))
        }
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}