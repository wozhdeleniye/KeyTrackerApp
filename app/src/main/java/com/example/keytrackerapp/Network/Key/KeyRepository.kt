package com.example.keytrackerapp.Network.Key

import com.example.filmushits.Etities.RequestBodies.RegisterRequestBody
import com.example.keytrackerapp.domain.Entities.Models.KeyListModel
import com.example.keytrackerapp.domain.Entities.Models.TokenModel

class KeyRepository (
    private val api: KeyApi
) {
    suspend fun getKeys(): KeyListModel {
        return api.getKeys()
    }
}