package com.example.keytrackerapp.View.LogRegScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmushits.Etities.RequestBodies.LoginRequestBody
import com.example.filmushits.Etities.RequestBodies.RegisterRequestBody
import com.example.filmushits.Network.Auth.AuthRepository
import com.example.filmushits.Network.Network
import com.example.keytrackerapp.domain.UseCases.AuthUseCases.LogOutUseCase
import com.example.keytrackerapp.domain.UseCases.AuthUseCases.LoginUseCase
import com.example.keytrackerapp.domain.UseCases.AuthUseCases.RegisterUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    fun register(registerData: RegisterRequestBody): Job {
        return viewModelScope.launch {
            val result = RegisterUseCase(AuthRepository(Network.getInstance().getAuthApi())).invoke(registerData)
            if (result.isSuccess) Network.getInstance().getTokenManager().setToken(result.getOrNull()!!.token)
            else cancel()
        }
    }

    fun login(loginData: LoginRequestBody): Job {
        return viewModelScope.launch {
            val result = LoginUseCase(AuthRepository(Network.getInstance().getAuthApi())).invoke(loginData)
            if (result.isSuccess) {
                Network.getInstance().getTokenManager().setToken(result.getOrNull()!!.token)
            }
            else cancel()
        }
    }
    fun logout(): Job {
        return viewModelScope.launch {
            val result = LogOutUseCase(AuthRepository(Network.getInstance().getAuthApi())).invoke()
            if (result.isSuccess) {
            }
            else cancel()
        }
    }
}
