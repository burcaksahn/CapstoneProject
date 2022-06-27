package com.burcak.sahin.myapplication.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.burcak.sahin.myapplication.ui.login.LoginViewModel

class RegisterFragmentViewModelFactory :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterFragmentViewmodel() as T
    }
}