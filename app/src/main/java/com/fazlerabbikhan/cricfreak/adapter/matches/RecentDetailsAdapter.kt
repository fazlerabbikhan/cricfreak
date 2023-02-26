package com.fazlerabbikhan.cricfreak.adapter.matches

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fazlerabbikhan.cricfreak.fragments.matches.*
import com.fazlerabbikhan.cricfreak.model.Tab

class RecentDetailsAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val tabList = listOf(
            Tab(InfoFragment(), "info"),
            Tab(BattingFragment(), "batting"),
            Tab(BowlingFragment(), "bowling"),
            Tab(LineupFragment(), "lineup")
        )
    }

    override fun createFragment(position: Int): Fragment {
        return RecentDetailsAdapter.tabList[position].fragment
    }

    override fun getItemCount(): Int {
        return RecentDetailsAdapter.tabList.size
    }
}