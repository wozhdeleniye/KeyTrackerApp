package com.example.keytrackerapp.domain.UseCases.AuthUseCases

import com.example.filmushits.Etities.RequestBodies.LoginRequestBody
import com.example.filmushits.Network.Auth.AuthRepository
import com.example.keytrackerapp.domain.Entities.Models.TokenModel
import kotlinx.coroutines.Job

class LogOutUseCase ( private val repository: AuthRepository
) {
    suspend fun invoke(): Result<Unit> {
        return try {
            Result.success(repository.logout())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}