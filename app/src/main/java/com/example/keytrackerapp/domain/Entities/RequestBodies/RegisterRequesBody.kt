package com.example.filmushits.Etities.RequestBodies


data class RegisterRequestBody(
    var fullname: String,
    var login: String,
    var password: String
)