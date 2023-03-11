package com.fazlerabbikhan.cricfreak.fragments.rankings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.rankings.T20IAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentT20iBinding
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class T20IFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private lateinit var t20iRecyclerView: RecyclerView

    private var _binding: FragmentT20iBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentT20iBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        t20iRecyclerView = binding.t20iRecycler

        viewModel.catchT20IRankings()

        viewModel.t20irankings.observe(viewLifecycleOwner){
            val adapterViewState = t20iRecyclerView.layoutManager?.onSaveInstanceState()
            t20iRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            try{
                t20iRecyclerView.adapter = T20IAdapter(it[0].team)
            } catch (e: java.lang.Exception) {
                Log.d("Exception","$e")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}