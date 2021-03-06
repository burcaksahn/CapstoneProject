package com.burcak.sahin.myapplication.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClickListener()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun initClickListener() {

    }
}