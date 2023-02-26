package com.fazlerabbikhan.cricfreak.fragments.matches

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fazlerabbikhan.cricfreak.adapter.matches.BattingAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentBattingBinding
import com.fazlerabbikhan.cricfreak.global.Constant

class BattingFragment : Fragment() {
    private lateinit var battingRecyclerView: RecyclerView

    private var _binding: FragmentBattingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBattingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        battingRecyclerView = binding.battingRecycler

        val battingLocal = Constant.recentDetails?.batting?.filter {
            it.team_id == Constant.recentDetails?.localteam_id
        }

        val battingVisitor = Constant.recentDetails?.batting?.filter {
            it.team_id == Constant.recentDetails?.visitorteam_id
        }

        val adapterViewState = battingRecyclerView.layoutManager?.onSaveInstanceState()
        battingRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
        battingRecyclerView.adapter = BattingAdapter(battingLocal)

        binding.buttonLocal.text = Constant.recentDetails?.localteam?.name
        binding.buttonVisitor.text = Constant.recentDetails?.visitorteam?.name

        val buttonLocal = binding.buttonLocal
        val buttonVisitor = binding.buttonVisitor
        var clickedLocal = 0
        var clickedVisitor = 0

        buttonLocal.setOnClickListener(){
            val adapterViewState = battingRecyclerView.layoutManager?.onSaveInstanceState()
            battingRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            battingRecyclerView.adapter = BattingAdapter(battingLocal)

            clickedLocal += 1
            if(clickedLocal > 1) clickedLocal = 1
            when(clickedLocal){
                1-> {
                    buttonLocal.setBackgroundColor(Color.parseColor("#6495ED"))
                    buttonLocal.setTextColor(Color.parseColor("#FFFFFF"))
                    buttonVisitor.setBackgroundColor(Color.parseColor("#DEB887"))
                    buttonVisitor.setTextColor(Color.parseColor("#000000"))
                }
            }
        }

        buttonVisitor.setOnClickListener(){
            val adapterViewState = battingRecyclerView.layoutManager?.onSaveInstanceState()
            battingRecyclerView.layoutManager?.onRestoreInstanceState(adapterViewState)
            battingRecyclerView.adapter = BattingAdapter(battingVisitor)

            clickedVisitor += 1
            if(clickedVisitor > 1) clickedVisitor = 1
            when(clickedVisitor){
                1-> {
                    buttonVisitor.setBackgroundColor(Color.parseColor("#6495ED"))
                    buttonVisitor.setTextColor(Color.parseColor("#FFFFFF"))
                    buttonLocal.setBackgroundColor(Color.parseColor("#DEB887"))
                    buttonLocal.setTextColor(Color.parseColor("#000000"))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}