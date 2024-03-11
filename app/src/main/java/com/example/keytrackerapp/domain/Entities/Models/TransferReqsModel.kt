package com.example.keytrackerapp.domain.Entities.Models

import com.example.keytrackerapp.domain.Entities.Enums.RequestStatusEnum

data class TransferReqsModel (
    val id: String,
    val userFrom: UserModel,
    val userTo: UserModel,
    val keyDTO: KeyModel
)