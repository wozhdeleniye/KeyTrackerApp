package com.example.keytrackerapp.domain.UseCases.RequestUseCases

import com.example.keytrackerapp.Network.Request.RequestRepository
import com.example.keytrackerapp.domain.Entities.Models.ReqListModel
import com.example.keytrackerapp.domain.Entities.RequestBodies.CreateReqBody

class GetReqListUseCase (
    private val repository: RequestRepository
) {
    suspend fun invoke(): Result<ReqListModel> {
        return try {
            Result.success(repository.getReqList())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}