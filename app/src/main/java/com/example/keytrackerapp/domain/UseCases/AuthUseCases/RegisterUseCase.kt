package com.example.keytrackerapp.domain.UseCases.AuthUseCases

import com.example.filmushits.Etities.RequestBodies.RegisterRequestBody
import com.example.filmushits.Network.Auth.AuthRepository
import com.example.keytrackerapp.domain.Entities.Models.TokenModel

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend fun invoke(body: RegisterRequestBody): Result<TokenModel> {
        return try {
            Result.success(repository.register(body))
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}