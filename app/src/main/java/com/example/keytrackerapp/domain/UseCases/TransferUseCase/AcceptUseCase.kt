package com.example.keytrackerapp.domain.UseCases.TransferUseCase

import com.example.keytrackerapp.Network.Transfer.TransferRepository
import com.example.keytrackerapp.domain.Entities.Models.TransferReqsModel

class AcceptUseCase (
    private val repository: TransferRepository
) {
    suspend fun invoke(id: String, submit: String): Result<Unit> {
        return try {
            Result.success(repository.accept(id, submit))
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}