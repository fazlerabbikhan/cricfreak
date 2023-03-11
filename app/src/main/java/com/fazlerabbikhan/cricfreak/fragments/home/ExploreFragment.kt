package com.fazlerabbikhan.cricfreak.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.home.ExploreAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentExploreBinding
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class ExploreFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private lateinit var exploreRecyclerView: RecyclerView

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        exploreRecyclerView = binding.teamRecycler

        viewModel.catchTeams()
        viewModel.readTeamData

        viewModel.teams.observe(viewLifecycleOwner){
            val adapterViewState = exploreRecyclerView.layoutManager?.onSaveInstanceState()
            exploreRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            exploreRecyclerView.adapter = ExploreAdapter(it)
        }

        viewModel.readTeamData.observe(viewLifecycleOwner){
            val adapterViewState = exploreRecyclerView.layoutManager?.onSaveInstanceState()
            exploreRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            exploreRecyclerView.adapter = ExploreAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}