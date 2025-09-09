package com.example.authdemo.di

import com.example.authdemo.data.ApiService
import com.example.authdemo.data.UserAuthRepositoryImpl
import com.example.authdemo.data.local.DataStoreManager
import com.example.authdemo.domain.repository.UserAuthRepository
import com.example.authdemo.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService= retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService,dataStoreManager: DataStoreManager): UserAuthRepository=
       UserAuthRepositoryImpl(
            apiService = apiService,
            dataStoreManager = dataStoreManager
        )

}