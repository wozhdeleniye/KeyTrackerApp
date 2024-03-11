package com.example.keytrackerapp.Network.Transfer

import com.example.keytrackerapp.Network.Request.RequestApi
import com.example.keytrackerapp.domain.Entities.Models.ReqListModel
import com.example.keytrackerapp.domain.Entities.Models.TransferReqsModel
import com.example.keytrackerapp.domain.Entities.Models.UserListModel

class TransferRepository (
    private val api: TransferApi
) {
    suspend fun transitOffer(key: String, user: String) {
        return api.transit(key, user)
    }
    suspend fun getUsers() : UserListModel {
        return api.getUsers()
    }
    suspend fun getNotifications() : List<TransferReqsModel> {
        return api.getNotifications()
    }
    suspend fun accept(id: String, submit: String) {
        return api.accept(id, submit)
    }
}