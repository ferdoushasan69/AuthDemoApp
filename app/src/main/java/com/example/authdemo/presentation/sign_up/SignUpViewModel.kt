package com.example.authdemo.presentation.sign_up
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authdemo.data.model.SignUpRequest
import com.example.authdemo.data.model.SignUpResponse
import com.example.authdemo.domain.use_case.SignUpUseCase
import com.example.authdemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _signUpState = MutableStateFlow<Resource<SignUpResponse>>(Resource.Idle())
    val signUpState: StateFlow<Resource<SignUpResponse>> = _signUpState.asStateFlow()

    fun signUp(username: String, email: String, password: String, role: String = "user") {
        viewModelScope.launch {
            val request = SignUpRequest(username, email, password, role)
            signUpUseCase(request).collect { result ->
                _signUpState.value = result
            }
        }
    }
}