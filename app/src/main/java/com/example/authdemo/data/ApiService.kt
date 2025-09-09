package com.example.authdemo.data

import com.example.authdemo.data.model.LoginResponse
import com.example.authdemo.data.model.SignUpRequest
import com.example.authdemo.data.model.SignUpResponse
import com.example.authdemo.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST




interface ApiService {

    @POST("auth/register")
    @Headers("Content-Type: application/json")
    suspend fun register(@Body request: SignUpRequest): SignUpResponse

    @POST("auth/login")
    suspend fun login(@Body request: Map<String, String>): LoginResponse

    @GET("hello_world")
    suspend fun getHelloWorld(@Header("Authorization") token: String): User
}
