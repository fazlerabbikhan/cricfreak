package com.fazlerabbikhan.cricfreak.fragments.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.matches.LiveAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentLiveBinding
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class LiveFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private lateinit var liveRecyclerView: RecyclerView

    private var _binding: FragmentLiveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        liveRecyclerView = binding.liveRecycler

        viewModel.catchLivescores()

        viewModel.livescores.observe(viewLifecycleOwner){
            val adapterViewState = liveRecyclerView.layoutManager?.onSaveInstanceState()
            liveRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            liveRecyclerView.adapter = LiveAdapter(it)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            //getTypeArticles(type)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}