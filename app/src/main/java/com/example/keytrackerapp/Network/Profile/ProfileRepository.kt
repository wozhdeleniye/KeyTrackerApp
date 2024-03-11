package com.example.keytrackerapp.Network.Profile

import com.example.keytrackerapp.domain.Entities.Models.ProfileModel

class ProfileRepository(
    private val api: ProfileApi
) {
    suspend fun getProfile(): ProfileModel {
        return api.getProfile()
    }
}