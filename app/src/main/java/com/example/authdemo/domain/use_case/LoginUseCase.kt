package com.example.authdemo.domain.use_case

import com.example.authdemo.domain.repository.UserAuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: UserAuthRepository) {
    suspend operator fun invoke(email: String, password: String) =
        repository.login(email, password)
}