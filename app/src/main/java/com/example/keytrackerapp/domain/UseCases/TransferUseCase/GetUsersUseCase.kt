package com.example.keytrackerapp.domain.UseCases.TransferUseCase

import com.example.keytrackerapp.Network.Transfer.TransferRepository
import com.example.keytrackerapp.domain.Entities.Models.UserListModel

class GetUsersUseCase (
    private val repository: TransferRepository
) {
    suspend fun invoke(): Result<UserListModel> {
        return try {
            Result.success(repository.getUsers())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}