package com.example.keytrackerapp.domain.Entities.Models

import com.example.keytrackerapp.domain.Entities.Enums.RequestStatusEnum

data class ReqModel (
    val id: String,
    val name: String,
    val number: Int,
    val status: RequestStatusEnum,
    val requestCreationDateTime: String,
    val requestedDateTime: String,
    val keyId: String,
    val officeid: String
)