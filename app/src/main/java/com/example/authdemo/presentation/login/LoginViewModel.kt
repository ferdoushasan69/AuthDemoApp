package com.example.authdemo.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authdemo.data.model.LoginResponse
import com.example.authdemo.domain.use_case.LoginUseCase
import com.example.authdemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<LoginResponse>>(Resource.Idle())
    val loginState = _loginState.asStateFlow()
    fun login(email : String,password : String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading()
            try {
                loginUseCase(email = email, password = password).collect {
                    _loginState.value = it

                }
            } catch (e: Exception) {
                _loginState.value = Resource.Error(e.message ?: "Unknown error")
                Log.e("TAG", "login: ${e.localizedMessage}", )
            }
        }
    }
}