package com.example.authdemo.data

import com.example.authdemo.data.local.DataStoreManager
import com.example.authdemo.data.model.LoginResponse
import com.example.authdemo.data.model.SignUpRequest
import com.example.authdemo.data.model.SignUpResponse
import com.example.authdemo.data.model.User
import com.example.authdemo.domain.repository.UserAuthRepository
import com.example.authdemo.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreManager: DataStoreManager
) : UserAuthRepository {

    override suspend fun signUp(signUpRequest: SignUpRequest): Flow<Resource<SignUpResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.register(signUpRequest)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Sign up failed"))
        }
    }

    override suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            val loginBody = mapOf("email" to email, "password" to password)
            val response = apiService.login(loginBody)

            if (response.token.isNullOrBlank()) {
                emit(Resource.Error("Email or password not found"))
            } else {
                dataStoreManager.saveUserToken(response.token)
                emit(Resource.Success(response))
            }

        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error("Email or password not found"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Login failed"))
        }
    }

    override suspend fun getHelloWorld(): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val token = dataStoreManager.getUserToken().first() ?: ""
            val bearerToken = "Bearer $token"
            val response = apiService.getHelloWorld(bearerToken)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Failed to fetch user"))
        }
    }

    override suspend fun logout() {
            dataStoreManager.clearUserData()
    }
}


