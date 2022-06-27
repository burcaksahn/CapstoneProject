package com.burcak.sahin.myapplication.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.databinding.FragmentRegisterBinding
import com.burcak.sahin.myapplication.ui.login.LoginViewModel
import com.burcak.sahin.myapplication.ui.login.LoginViewModelFactory
import com.burcak.sahin.myapplication.ui.register.data.RegisterModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var vm: RegisterFragmentViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initClickListener()
        super.onViewCreated(view, savedInstanceState)
    }
    fun initClickListener() {
        binding.signUpButton.setOnClickListener {
            register()
        }
    }
    fun register() {
        if(!binding.emailText.text.toString().isNullOrEmpty() && !binding.passwordText.text.toString().isNullOrEmpty()) {
           vm.register(RegisterModel(binding.emailText.text.toString(), password = binding.passwordText.text.toString()))
        }
    }
    fun initVM() {
        vm = ViewModelProvider(
            this,
            RegisterFragmentViewModelFactory()
        ).get(RegisterFragmentViewmodel::class.java)
    }
}