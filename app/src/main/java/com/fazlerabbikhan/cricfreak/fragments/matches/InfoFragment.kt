package com.fazlerabbikhan.cricfreak.fragments.matches

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.databinding.FragmentInfoBinding
import com.fazlerabbikhan.cricfreak.global.Constant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormatter(date: String): String{
        val formatter = DateTimeFormatter.ISO_INSTANT
        val dateTime = LocalDateTime.parse(date, formatter.withZone(ZoneOffset.UTC))

        val formattedDate = dateTime.toLocalDate()

        return formattedDate.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.infoStage.text = getString(R.string.info_stage, Constant.recentDetails?.stage?.name)
        binding.infoRound.text = getString(R.string.info_round, Constant.recentDetails?.round)
        binding.infoDate.text = getString(R.string.info_date, Constant.recentDetails?.starting_at?.let { dateFormatter(it) })
        binding.infoLeague.text = getString(R.string.info_league, Constant.recentDetails?.league?.name)
        binding.infoVenue.text = getString(R.string.info_venue, Constant.recentDetails?.venue?.name)
        binding.infoCity.text = getString(R.string.info_city, Constant.recentDetails?.venue?.city)
        binding.infoCapacity.text = getString(R.string.info_capacity, Constant.recentDetails?.venue?.capacity.toString())
        binding.infoToss.text = getString(R.string.info_toss, Constant.recentDetails?.tosswon?.name)
        binding.infoElected.text = getString(R.string.info_elected, Constant.recentDetails?.elected)
        binding.infoFirstUmpire.text = getString(R.string.info_first_umpire, Constant.recentDetails?.firstumpire?.fullname)
        binding.infoSecondUmpire.text = getString(R.string.info_second_umpire, Constant.recentDetails?.secondumpire?.fullname)
        binding.infoTvUmpire.text = getString(R.string.info_tv_umpire, Constant.recentDetails?.tvumpire?.fullname)
        binding.infoReferee.text = getString(R.string.info_referee, Constant.recentDetails?.referee?.fullname)

    }
}