package com.example.keytrackerapp.domain.Entities.Models

data class KeyModel (
    val keyId: String,
    val officeID: String,
    val officeName: String,
    val officeNumber: Int,
    val userViewDTO: userViewDTO
)