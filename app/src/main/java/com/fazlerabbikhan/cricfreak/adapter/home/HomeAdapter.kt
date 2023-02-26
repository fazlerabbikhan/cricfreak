package com.fazlerabbikhan.cricfreak.adapter.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fazlerabbikhan.cricfreak.fragments.home.ExploreFragment
import com.fazlerabbikhan.cricfreak.fragments.home.PlayersFragment
import com.fazlerabbikhan.cricfreak.model.Tab

class HomeAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val tabList = listOf(
            Tab(ExploreFragment(), "explore"),
            Tab(PlayersFragment(), "players"),
        )
    }

    override fun createFragment(position: Int): Fragment {
        return tabList[position].fragment
    }

    override fun getItemCount(): Int {
        return tabList.size
    }
}