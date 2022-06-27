package com.burcak.sahin.myapplication.ui.home.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.databinding.FragmentProfileBinding
import com.burcak.sahin.myapplication.util.ProfileAdapter
import com.burcak.sahin.myapplication.util.updateWithUrl

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
        initRV()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun getDummyList(): List<String> {
        return listOf<String>("My Orders", "Shopping Addresses", "Payment Methods", "Promocodes", "My Reviews", "Settings")
    }
    private fun initUI() {
        binding.textViewName.text = "Burçak Şahin"
        binding.textViewEmail.text = "burcaksahn@gmail.com"
        binding.imageProfilePhoto.updateWithUrl("https://media-exp1.licdn.com/dms/image/C4D03AQFtrCBzW-IVLQ/profile-displayphoto-shrink_800_800/0/1641205347768?e=1661990400&v=beta&t=Dlp2ci5PahlBpJAx85XDdAW8upSW31JQ5XKxFOy5On8", binding.imageProfilePhoto)
    }
    private fun initRV() {
        adapter = ProfileAdapter()
        adapter.submitList(getDummyList())
        binding.profilerv.layoutManager = LinearLayoutManager(context)
        binding.profilerv.adapter = adapter
    }
}