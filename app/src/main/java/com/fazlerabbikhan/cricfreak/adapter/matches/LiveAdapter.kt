package com.fazlerabbikhan.cricfreak.adapter.matches

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.model.livescores.LivescoreData

class LiveAdapter(
    private val dataset: List<LivescoreData>?
) : RecyclerView.Adapter<LiveAdapter.LiveViewHolder>() {

    class LiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_live, parent, false)
        return LiveViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}