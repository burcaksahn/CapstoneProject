package com.burcak.sahin.myapplication.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burcak.sahin.myapplication.ui.login.data.LoginModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    fun auth(loginModel: LoginModel): MutableLiveData<Boolean> {
        auth = Firebase.auth
        val isSuccess = MutableLiveData<Boolean>()
        auth.signInWithEmailAndPassword(loginModel.email, loginModel.password).addOnCompleteListener {
            isSuccess.value = it.isSuccessful
        }
        return isSuccess
    }
}