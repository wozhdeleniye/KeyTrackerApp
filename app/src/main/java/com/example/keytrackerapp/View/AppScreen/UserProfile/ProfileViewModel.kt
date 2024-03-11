package com.example.keytrackerapp.View.AppScreen.UserProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmushits.Network.Network
import com.example.keytrackerapp.Network.Profile.ProfileRepository
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import com.example.keytrackerapp.domain.UseCases.ProfileUseCases.ProfileGetUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class ProfileViewModel: ViewModel() {
    fun getProfile(): Deferred<ProfileModel?> {
        return viewModelScope.async {
            val result = ProfileGetUseCase(ProfileRepository(Network.getInstance().getProfileApi())).invoke()
            result.getOrNull()
        }
    }
}