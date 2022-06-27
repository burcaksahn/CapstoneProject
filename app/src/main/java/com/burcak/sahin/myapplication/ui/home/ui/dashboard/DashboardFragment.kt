package com.burcak.sahin.myapplication.ui.home.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.burcak.sahin.myapplication.databinding.FragmentDashboardBinding
import com.burcak.sahin.myapplication.ui.home.ui.categorydetail.CategoryDetailActivity
import com.burcak.sahin.myapplication.util.CategoryAdapter
import com.burcak.sahin.myapplication.util.HomeRecyclerViewAdapter

class DashboardFragment : Fragment(), CategoryListener {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewmodel: DashboardViewModel
    private lateinit var adapter: CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initRecycler()
        getCategories()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun initVM() {
        viewmodel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

    }
    private fun initRecycler() {
        adapter = CategoryAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }
    private fun getCategories() {
        viewmodel.getCategories().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clicked(categoryName: String) {
        val intent = Intent(context, CategoryDetailActivity::class.java)
        intent.putExtra("key", categoryName)
        requireActivity().startActivity(intent)
    }
}