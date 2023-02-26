package com.fazlerabbikhan.cricfreak.adapter.rankings

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fazlerabbikhan.cricfreak.fragments.rankings.ODIFragment
import com.fazlerabbikhan.cricfreak.fragments.rankings.T20IFragment
import com.fazlerabbikhan.cricfreak.fragments.rankings.TestFragment
import com.fazlerabbikhan.cricfreak.model.Tab

class RankingsAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    companion object {
        val tabList = listOf(
            Tab(T20IFragment(), "t20i"),
            Tab(ODIFragment(), "odi"),
            Tab(TestFragment(), "test")
        )
    }

    override fun createFragment(position: Int): Fragment {
        return tabList[position].fragment
    }

    override fun getItemCount(): Int {
        return tabList.size
    }
}