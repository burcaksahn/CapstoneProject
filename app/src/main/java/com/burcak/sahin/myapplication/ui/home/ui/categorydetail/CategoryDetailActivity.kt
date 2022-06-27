package com.burcak.sahin.myapplication.ui.home.ui.categorydetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.databinding.ActivityCategoryDetailBinding
import com.burcak.sahin.myapplication.util.CategoryAdapter
import com.burcak.sahin.myapplication.util.CategoryDetailAdapter

class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var viewModel: CategoryDetailViewModel
    private lateinit var adapter: CategoryDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_category_detail
        )
        supportActionBar?.hide()
        initVM()
        initRecycler()
        bindItem()
        finishObserve()
    }

    private fun bindItem() {
        val id = intent.getStringExtra("key").toString()
        getData(id)

    }

    private fun initVM() {
        viewModel = ViewModelProviders.of(this).get(CategoryDetailViewModel::class.java)

    }
    private fun initRecycler() {
        adapter = CategoryDetailAdapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)

    }

    private fun getData(categoryName: String) {
        if (categoryName != "null") {
            viewModel.getData(categoryName).observe(
                this, Observer {
                    binding.dataModel = viewModel
                    adapter.submitList(it)
                }
            )
        }
    }

    private fun finishObserve() {
        viewModel.finishState?.observe(this, Observer {
            if (it == true) {
                this.finish()
                viewModel.finishStateRemove()
            }
        })
    }
}