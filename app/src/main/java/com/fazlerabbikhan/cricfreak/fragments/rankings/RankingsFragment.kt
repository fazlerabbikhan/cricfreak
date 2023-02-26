package com.fazlerabbikhan.cricfreak.fragments.rankings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.adapter.rankings.RankingsAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentRankingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class RankingsFragment : Fragment() {
    private var _binding: FragmentRankingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRankingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Tab layout
        val tabLayout = binding.tabLayoutRankings
        val viewPage = binding.viewPager2

        val tabAdapter = RankingsAdapter(childFragmentManager, lifecycle)
        viewPage.adapter = tabAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = RankingsAdapter.tabList[position].title
        }.attach()

//        Bottom navigation show
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility =
            View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}