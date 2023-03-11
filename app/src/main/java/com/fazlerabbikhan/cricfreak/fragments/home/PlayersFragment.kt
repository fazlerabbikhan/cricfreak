package com.fazlerabbikhan.cricfreak.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.fazlerabbikhan.cricfreak.R
import com.fazlerabbikhan.cricfreak.adapter.home.PlayersAdapter
import com.fazlerabbikhan.cricfreak.databinding.FragmentPlayersBinding
import com.fazlerabbikhan.cricfreak.model.players.PlayersData
import com.fazlerabbikhan.cricfreak.viewmodel.CricViewModel

class PlayersFragment : Fragment() {
    private lateinit var viewModel: CricViewModel
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricViewModel::class.java]

        viewModel.catchPlayers()

        // Search menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView: SearchView = searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                    android.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onQueryTextChange(msg: String): Boolean {
//                        filter data
                        val queryResult = mutableListOf<PlayersData>()
                        viewModel.players.value?.map {
                            if (it.lastname?.contains(msg, ignoreCase = true) == true) {
                                queryResult.add(it)
                            }
                        }
                        binding.playersRecycler.adapter = PlayersAdapter(queryResult)
                        Log.d("Query", "onQueryTextChange: ${queryResult.size}")
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}