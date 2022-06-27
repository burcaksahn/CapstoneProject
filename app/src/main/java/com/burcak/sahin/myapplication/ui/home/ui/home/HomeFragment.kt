package com.burcak.sahin.myapplication.ui.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.burcak.sahin.myapplication.databinding.FragmentHomeBinding
import com.burcak.sahin.myapplication.ui.home.ui.detail.DetailActivity
import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel
import com.burcak.sahin.myapplication.util.HomeRecyclerViewAdapter
import com.burcak.sahin.myapplication.util.ProductClickListener

class HomeFragment : Fragment(), ProductClickListener{

    private var _binding: FragmentHomeBinding? = null
    private lateinit var vm: HomeViewModel
    private val binding get() = _binding!!
    private lateinit var adapter: HomeRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
        getAllProduct()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun initUI() {
        adapter = HomeRecyclerViewAdapter(this)
        binding.newProdRv.adapter = adapter
        binding.newProdRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    private fun getAllProduct(){
        vm.getProduct().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun isProductCliked(productModel: ProductModel) {
        val intent = Intent(this.context, DetailActivity::class.java)
        intent.putExtra("key", productModel.id.toString())
        requireActivity().startActivity(intent)
    }
}