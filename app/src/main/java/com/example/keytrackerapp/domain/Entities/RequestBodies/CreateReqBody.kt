package com.example.keytrackerapp.domain.Entities.RequestBodies

data class CreateReqBody (
    var requestedDateTime: String,
    var keyId: String,
    var officeId: String,
)