package com.example.authdemo.domain.repository

import com.example.authdemo.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.example.authdemo.data.model.LoginResponse
import com.example.authdemo.data.model.SignUpRequest
import com.example.authdemo.data.model.SignUpResponse
import com.example.authdemo.data.model.User



interface UserAuthRepository {
    suspend fun signUp(signUpRequest: SignUpRequest): Flow<Resource<SignUpResponse>>
    suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>>
    suspend fun getHelloWorld(): Flow<Resource<User>>

    suspend fun logout()
}
