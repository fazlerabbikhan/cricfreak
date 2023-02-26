package com.fazlerabbikhan.cricfreak.fragments.rankings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.rankings.TestAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentTestBinding
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class TestFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private lateinit var testRecyclerView: RecyclerView

    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        testRecyclerView = binding.testRecycler

        viewModel.catchTestRankings()

        viewModel.testrankings.observe(viewLifecycleOwner){
            val adapterViewState = testRecyclerView.layoutManager?.onSaveInstanceState()
            testRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            testRecyclerView.adapter = TestAdapter(it[0].team)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}