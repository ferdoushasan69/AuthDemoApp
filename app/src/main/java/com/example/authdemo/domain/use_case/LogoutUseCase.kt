package com.example.authdemo.domain.use_case

import com.example.authdemo.domain.repository.UserAuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: UserAuthRepository
) {

    suspend operator fun invoke() = repository.logout()
}