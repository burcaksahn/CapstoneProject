package com.burcak.sahin.myapplication.ui.home.ui.mybag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.burcak.sahin.myapplication.databinding.FragmentMyBagBinding
import com.burcak.sahin.myapplication.ui.home.ui.mybag.data.MyBagModel
import com.burcak.sahin.myapplication.ui.home.ui.success.SuccessActivity
import com.burcak.sahin.myapplication.util.MyBagAdapter

class MyBagFragment : Fragment(), MyBagClickListener {
    private lateinit var binding: FragmentMyBagBinding
    private lateinit var adapter: MyBagAdapter

    companion object {
        fun newInstance() = MyBagFragment()
        val list = arrayListOf<MyBagModel>()
        fun addToList(model: MyBagModel, type: String = "plus") {
            if (list.contains(model)) {
                for (i in 0 until list.size) {
                    if (list.get(i) == model) {
                        if (type == "plus") {
                            model.count++

                        } else {
                            model.count--
                        }
                    }
                }
            } else {
                list.add(model)
            }
        }
        fun deleteList() {
            list.clear()
        }
    }

    private lateinit var viewModel: MyBagViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MyBagViewModel::class.java)
        Log.d("TAG", "onViewCreated: " + list.size)
        initRv()
        getData()
        clickListener()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun initRv() {
        adapter = MyBagAdapter(this)
        binding.myBagRv.layoutManager = LinearLayoutManager(context)
        binding.myBagRv.adapter = adapter
    }
    private fun getData() {
        calculateTotalAmount()
        adapter.submitList(list)
    }
    private fun calculateTotalAmount() {
        var totalAmount = 0.0
        for(i in 0..list.size-1) {
            val currentAmount = list.get(i).count.toInt() * list.get(i).price.toDouble()
            totalAmount = totalAmount.toDouble() + currentAmount
        }
        binding.totalAmount.text = totalAmount.toString()
    }
    private fun clickListener() {
        binding.checkOutButton.setOnClickListener {
            deleteList()
            initRv()
            adapter.currentList.clear()
            adapter.submitList(list)
            adapter.notifyDataSetChanged()
            binding.totalAmount.text = "0.0"
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            requireActivity().startActivity(intent)
        }

    }
    override fun isPlus(myBagModel: MyBagModel) {
        addToList(myBagModel)
        calculateTotalAmount()
    }

    override fun isMinus(myBagModel: MyBagModel) {
        addToList(myBagModel, "minus")
        calculateTotalAmount()
    }

}