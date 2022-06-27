package com.burcak.sahin.myapplication.ui.home.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.databinding.ActivityDetailBinding
import com.burcak.sahin.myapplication.ui.home.ui.mybag.MyBagFragment
import com.burcak.sahin.myapplication.util.updateWithUrl
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_detail)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_detail
        )
        initVM()
        bindItem()
        clickListener()
        finishObserve()
    }

    private fun initVM() {
        viewModel = ViewModelProviders.of(this)[DetailViewModel::class.java]
        viewModel.setContext(context = this)

    }

    private fun clickListener() {
        binding.favic.setOnClickListener {
            lifecycleScope.launch {
                viewModel.insert()
            }
        }
        binding.addCardBtn.setOnClickListener {
            viewModel.insertBag()
        }
    }

    private fun bindItem() {
        val id = intent.getStringExtra("key").toString()
        getData(id)

    }

    private fun getData(id: String) {
        if (id != "null") {
            viewModel.getData(id).observe(this, Observer {
                viewModel.detail = it
                binding.dataModel = viewModel
                binding.detailRV.updateWithUrl(it.image.toString(), binding.detailRV)
            })
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