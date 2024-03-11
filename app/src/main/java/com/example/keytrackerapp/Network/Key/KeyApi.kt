package com.example.keytrackerapp.Network.Key

import com.example.keytrackerapp.domain.Entities.Models.KeyListModel
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import retrofit2.http.GET

interface KeyApi {
    @GET("/keys/list")
    suspend fun getKeys(): KeyListModel
}