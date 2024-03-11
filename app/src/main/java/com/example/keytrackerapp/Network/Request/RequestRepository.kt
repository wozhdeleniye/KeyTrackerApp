package com.example.keytrackerapp.Network.Request

import com.example.filmushits.Etities.RequestBodies.RegisterRequestBody
import com.example.keytrackerapp.Network.Profile.ProfileApi
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import com.example.keytrackerapp.domain.Entities.Models.ReqListModel
import com.example.keytrackerapp.domain.Entities.Models.TokenModel
import com.example.keytrackerapp.domain.Entities.RequestBodies.CreateReqBody

class RequestRepository (
    private val api: RequestApi
) {
    suspend fun getReqList(): ReqListModel {
        return api.getReqList()
    }

    suspend fun createReq(body: CreateReqBody) {
        return api.createReq(body)
    }
}