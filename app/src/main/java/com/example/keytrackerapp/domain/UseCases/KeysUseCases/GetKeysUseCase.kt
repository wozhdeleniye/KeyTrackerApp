package com.example.keytrackerapp.domain.UseCases.KeysUseCases

import com.example.keytrackerapp.Network.Key.KeyRepository
import com.example.keytrackerapp.Network.Request.RequestRepository
import com.example.keytrackerapp.domain.Entities.Models.KeyListModel
import com.example.keytrackerapp.domain.Entities.Models.ReqListModel

class GetKeysUseCase (
    private val repository: KeyRepository
) {
    suspend fun invoke(): Result<KeyListModel> {
        return try {
            Result.success(repository.getKeys())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}