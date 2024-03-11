package com.example.keytrackerapp.Network.Request

import com.example.filmushits.Etities.RequestBodies.RegisterRequestBody
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import com.example.keytrackerapp.domain.Entities.Models.ReqListModel
import com.example.keytrackerapp.domain.Entities.RequestBodies.CreateReqBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RequestApi {
    @GET("/request/list")
    suspend fun getReqList(): ReqListModel
    @POST("/request/create")
    suspend fun createReq(@Body body: CreateReqBody)
}