package com.example.authdemo.domain.use_case

import com.example.authdemo.data.model.SignUpRequest
import com.example.authdemo.domain.repository.UserAuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: UserAuthRepository) {
    suspend operator fun invoke(signUpRequest: SignUpRequest) =
        repository.signUp(signUpRequest)
}