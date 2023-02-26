package com.fazlerabbikhan.cricfreak.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.matches.UpcomingAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentUpcomingBinding
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class UpcomingFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private lateinit var upcomingRecyclerView: RecyclerView

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        upcomingRecyclerView = binding.upcomingRecycler

        viewModel.catchUpcoming()

        viewModel.upcoming.observe(viewLifecycleOwner){
            val adapterViewState = upcomingRecyclerView.layoutManager?.onSaveInstanceState()
            upcomingRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            upcomingRecyclerView.adapter = UpcomingAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}