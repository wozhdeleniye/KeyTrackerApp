package com.example.keytrackerapp.domain.UseCases.ProfileUseCases

import com.example.keytrackerapp.Network.Profile.ProfileRepository
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel

class ProfileGetUseCase(
    private val repository: ProfileRepository
) {
    suspend fun invoke(): Result<ProfileModel> {
        return try {
            Result.success(repository.getProfile())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}