package com.burcak.sahin.myapplication.ui.home.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.burcak.sahin.myapplication.databinding.FragmentFavoritesBinding
import com.burcak.sahin.myapplication.ui.home.ui.detail.data.db.FavDataBase
import com.burcak.sahin.myapplication.util.FavoriteAdapter

class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: FavoriteAdapter
    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        viewModel.setContext(requireContext())
        initRV()
        getData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRV() {
        adapter = FavoriteAdapter()
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = adapter

    }
    private var database: FavDataBase? = null

    private fun getData() {
        database = FavDataBase.getInstance(requireContext())
        database?.favDao()?.getAllFav()?.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "getData:  " +  it.size)
            adapter.submitList(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}