package com.burcak.sahin.myapplication.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burcak.sahin.myapplication.ui.register.data.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragmentViewmodel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    fun register(registerModel: RegisterModel): MutableLiveData<Boolean> {
        auth = Firebase.auth
        val isSuccess = MutableLiveData<Boolean>()
        auth.createUserWithEmailAndPassword(registerModel.email, registerModel.password).addOnCompleteListener {
            isSuccess.value = it.isSuccessful
        }
        return isSuccess
    }
}