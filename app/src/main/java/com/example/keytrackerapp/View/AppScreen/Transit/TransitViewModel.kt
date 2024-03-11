package com.example.keytrackerapp.View.AppScreen.Transit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmushits.Network.Network
import com.example.keytrackerapp.Network.Profile.ProfileRepository
import com.example.keytrackerapp.Network.Transfer.TransferRepository
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import com.example.keytrackerapp.domain.Entities.Models.TransferReqsModel
import com.example.keytrackerapp.domain.Entities.Models.UserListModel
import com.example.keytrackerapp.domain.UseCases.ProfileUseCases.ProfileGetUseCase
import com.example.keytrackerapp.domain.UseCases.TransferUseCase.AcceptUseCase
import com.example.keytrackerapp.domain.UseCases.TransferUseCase.GetNotificationsUseCase
import com.example.keytrackerapp.domain.UseCases.TransferUseCase.GetUsersUseCase
import com.example.keytrackerapp.domain.UseCases.TransferUseCase.TransitUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

class TransitViewModel: ViewModel() {
    fun getUsers(): Deferred<UserListModel?> {
        return viewModelScope.async {
            val result = GetUsersUseCase(TransferRepository(Network.getInstance().getTransferApi())).invoke()
            result.getOrNull()
        }
    }

    fun transitOffer(key: String, user: String): Job {
        return viewModelScope.async {
            val result = TransitUseCase(TransferRepository(Network.getInstance().getTransferApi())).invoke(key, user)
            result.getOrNull()
        }
    }
    fun getNotifications(): Deferred<List<TransferReqsModel>?> {
        return viewModelScope.async {
            val result = GetNotificationsUseCase(TransferRepository(Network.getInstance().getTransferApi())).invoke()
            result.getOrNull()
        }
    }
    fun accept(id: String, submit: String): Job {
        return viewModelScope.async {
            val result = AcceptUseCase(TransferRepository(Network.getInstance().getTransferApi())).invoke(id, submit)
            result.getOrNull()
        }
    }
}