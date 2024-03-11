package com.example.keytrackerapp.domain.UseCases.AuthUseCases

import com.example.filmushits.Etities.RequestBodies.LoginRequestBody
import com.example.filmushits.Network.Auth.AuthRepository
import com.example.keytrackerapp.domain.Entities.Models.TokenModel

class LoginUseCase( private val repository: AuthRepository
) {
    suspend fun invoke(body: LoginRequestBody): Result<TokenModel> {
        return try {
            Result.success(repository.login(body))
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}