package com.fazlerabbikhan.cricfreak.fragments.matches

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.matches.RecentAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentRecentBinding
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class RecentFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private lateinit var recentRecyclerView: RecyclerView

    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        recentRecyclerView = binding.recentRecycler

        viewModel.catchRecentInternational()
        viewModel.catchRecentDomestic()

        viewModel.recentInternational.observe(viewLifecycleOwner){
            val adapterViewState = recentRecyclerView.layoutManager?.onSaveInstanceState()
            recentRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            recentRecyclerView.adapter = RecentAdapter(it)
        }

        val buttonInternational = binding.buttonInternational
        val buttonDomestic = binding.buttonDomestic
        var clickedInternational = 0
        var clickedDomestic = 0

        buttonInternational.setOnClickListener(){
            viewModel.recentInternational.observe(viewLifecycleOwner){
                val adapterViewState = recentRecyclerView.layoutManager?.onSaveInstanceState()
                recentRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
                recentRecyclerView.adapter = RecentAdapter(it)
            }
            clickedInternational += 1
            if(clickedInternational > 1) clickedInternational = 1
            when(clickedInternational){
                1-> {
                    buttonInternational.setBackgroundColor(Color.parseColor("#6495ED"))
                    buttonInternational.setTextColor(Color.parseColor("#FFFFFF"))
                    buttonDomestic.setBackgroundColor(Color.parseColor("#DEB887"))
                    buttonDomestic.setTextColor(Color.parseColor("#000000"))
                }
            }
        }

        buttonDomestic.setOnClickListener(){
            viewModel.recentDomestic.observe(viewLifecycleOwner){
                val adapterViewState = recentRecyclerView.layoutManager?.onSaveInstanceState()
                recentRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
                recentRecyclerView.adapter = RecentAdapter(it)
            }
            clickedDomestic += 1
            if(clickedDomestic > 1) clickedDomestic = 1
            when(clickedDomestic){
                1-> {
                    buttonDomestic.setBackgroundColor(Color.parseColor("#6495ED"))
                    buttonDomestic.setTextColor(Color.parseColor("#FFFFFF"))
                    buttonInternational.setBackgroundColor(Color.parseColor("#DEB887"))
                    buttonInternational.setTextColor(Color.parseColor("#000000"))
                }
            }
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