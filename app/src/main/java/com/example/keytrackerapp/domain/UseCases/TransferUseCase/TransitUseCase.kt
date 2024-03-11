package com.example.keytrackerapp.domain.UseCases.TransferUseCase

import com.example.keytrackerapp.Network.Transfer.TransferRepository

class TransitUseCase (
    private val repository: TransferRepository
) {
    suspend fun invoke(key: String, user: String): Result<Unit> {
        return try {
            Result.success(repository.transitOffer(key, user))
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}