package com.fazlerabbikhan.cricfreak.fragments.matches

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.adapter.matches.RecentDetailsAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentRecentDetailsBinding
import com.fazlerabbikhan.cricfreak.global.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class RecentDetailsFragment : Fragment() {
    private var _binding: FragmentRecentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val team1runs = Constant.recentDetails?.runs?.filter {
            it.inning == 1
        }
        val team2runs = Constant.recentDetails?.runs?.filter {
            it.inning == 2
        }

        if(team1runs != null && team1runs.isNotEmpty()){
            Glide
                .with(requireContext())
                .load(team1runs[0].team?.image_path)
                .fitCenter()
                .into(binding.recentDetailsTeam1Image)
            binding.recentDetailsTeam1Name.text = team1runs[0].team?.name
            if(team1runs[0].wickets != 10){
                binding.recentDetailsTeam1Score.text = "${team1runs[0].score}/${team1runs[0].wickets}"
            } else{
                binding.recentDetailsTeam1Score.text = "${team1runs[0].score}"
            }
            binding.recentDetailsTeam1Overs.text = "(${team1runs[0].overs})"
        }

        if(team2runs != null && team2runs.isNotEmpty()){
            Glide
                .with(requireContext())
                .load(team2runs[0].team?.image_path)
                .fitCenter()
                .into(binding.recentDetailsTeam2Image)
            binding.recentDetailsTeam2Name.text = team2runs[0].team?.name
            if(team2runs[0].wickets != 10){
                binding.recentDetailsTeam2Score.text = "${team2runs[0].score}/${team2runs[0].wickets}"
            } else{
                binding.recentDetailsTeam2Score.text = "${team2runs[0].score}"
            }
            binding.recentDetailsTeam2Overs.text = "(${team2runs[0].overs})"
        }

        binding.recentDetailsNote.text = Constant.recentDetails?.note

//        Tab layout
        val tabLayout = binding.tabLayoutRecentDetails
        val viewPage = binding.viewPager2

        val tabAdapter = RecentDetailsAdapter(childFragmentManager, lifecycle)
        viewPage.adapter = tabAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            tab.text = RecentDetailsAdapter.tabList[position].title
        }.attach()

//        Bottom navigation show
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility =
            View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}