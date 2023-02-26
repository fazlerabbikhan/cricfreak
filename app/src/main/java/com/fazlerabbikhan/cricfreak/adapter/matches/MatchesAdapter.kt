package com.fazlerabbikhan.cricfreak.adapter.matches

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fazlerabbikhan.cricfreak.fragments.matches.LiveFragment
import com.fazlerabbikhan.cricfreak.fragments.matches.RecentFragment
import com.fazlerabbikhan.cricfreak.fragments.matches.UpcomingFragment
import com.fazlerabbikhan.cricfreak.model.Tab

class MatchesAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val tabList = listOf(
            Tab(UpcomingFragment(), "upcoming"),
            Tab(RecentFragment(), "recent"),
            Tab(LiveFragment(), "live")
        )
    }

    override fun createFragment(position: Int): Fragment {
        return tabList[position].fragment
    }

    override fun getItemCount(): Int {
        return tabList.size
    }
}