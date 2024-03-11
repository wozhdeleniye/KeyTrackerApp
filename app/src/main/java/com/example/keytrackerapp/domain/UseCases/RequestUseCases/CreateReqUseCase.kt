package com.example.keytrackerapp.domain.UseCases.RequestUseCases


import com.example.filmushits.Etities.RequestBodies.LoginRequestBody
import com.example.keytrackerapp.Network.Request.RequestRepository
import com.example.keytrackerapp.domain.Entities.Models.TokenModel
import com.example.keytrackerapp.domain.Entities.RequestBodies.CreateReqBody

class CreateReqUseCase(
    private val repository: RequestRepository
) {
    suspend fun invoke(body: CreateReqBody): Result<Unit> {
        return try {
            Result.success(repository.createReq(body))
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}