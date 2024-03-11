package com.example.keytrackerapp.domain.UseCases.TransferUseCase

import com.example.keytrackerapp.Network.Transfer.TransferRepository
import com.example.keytrackerapp.domain.Entities.Models.TransferReqsModel
import com.example.keytrackerapp.domain.Entities.Models.UserListModel

class GetNotificationsUseCase (
    private val repository: TransferRepository
) {
    suspend fun invoke(): Result<List<TransferReqsModel>> {
        return try {
            Result.success(repository.getNotifications())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}