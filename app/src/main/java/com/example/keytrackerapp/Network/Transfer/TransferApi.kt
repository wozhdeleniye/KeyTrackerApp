package com.example.keytrackerapp.Network.Transfer

import com.example.keytrackerapp.domain.Entities.Models.TransferReqsModel
import com.example.keytrackerapp.domain.Entities.Models.UserListModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TransferApi {
    @POST("/keys/{key}/transit/{user}")
    suspend fun transit(@Path("key") key: String, @Path("user") user: String)
    @GET("/api/account/users")
    suspend fun getUsers() : UserListModel
    @GET("/keys/notifications")
    suspend fun getNotifications() : List<TransferReqsModel>
    @POST("/keys/submit/{id}")
    suspend fun accept(@Path ("id") id: String, @Query ("submit") submit: String)
}