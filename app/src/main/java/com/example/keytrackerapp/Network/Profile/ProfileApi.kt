package com.example.keytrackerapp.Network.Profile

import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import retrofit2.http.Body
import retrofit2.http.GET

interface ProfileApi {
    @GET("/api/account/profile")
    suspend fun getProfile(): ProfileModel
}