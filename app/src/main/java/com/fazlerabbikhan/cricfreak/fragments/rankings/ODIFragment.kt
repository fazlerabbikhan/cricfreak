package com.fazlerabbikhan.cricfreak.fragments.rankings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.rankings.ODIAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentOdiBinding
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class ODIFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private lateinit var odiRecyclerView: RecyclerView

    private var _binding: FragmentOdiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOdiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        odiRecyclerView = binding.odiRecycler

        viewModel.catchODIRankings()

        viewModel.odirankings.observe(viewLifecycleOwner){
            val adapterViewState = odiRecyclerView.layoutManager?.onSaveInstanceState()
            odiRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            odiRecyclerView.adapter = ODIAdapter(it[0].team)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}