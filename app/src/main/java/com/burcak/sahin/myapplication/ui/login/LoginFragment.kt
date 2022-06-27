package com.burcak.sahin.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.burcak.sahin.myapplication.R
import com.burcak.sahin.myapplication.databinding.FragmentLoginBinding
import com.burcak.sahin.myapplication.databinding.FragmentRegisterBinding
import com.burcak.sahin.myapplication.ui.home.HomeActivity
import com.burcak.sahin.myapplication.ui.login.data.LoginModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var VM: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initClickListener()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun initVM() {
        VM = ViewModelProvider(
            this,
            LoginViewModelFactory()
        ).get(LoginViewModel::class.java)
    }
    private fun initClickListener() {
        binding.btnShareCode.setOnClickListener {
            auth()
        }
        binding.registerFragment.setOnClickListener {
            routeFragments(R.id.action_loginFragment_to_registerFragment)
        }
    }
    private fun auth() {
        if(!binding.emailText.text.toString().isNullOrEmpty() && !binding.passwordText.text.toString().isNullOrEmpty()) {
            VM.auth(LoginModel(binding.emailText.text.toString(), binding.passwordText.text.toString())).observe(viewLifecycleOwner, Observer {
                if(it) {
                    val intent = Intent(context, HomeActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            })
        }
    }
    private fun routeFragments(id: Int) {
        Navigation.findNavController(binding.root).navigate(id)
    }
}