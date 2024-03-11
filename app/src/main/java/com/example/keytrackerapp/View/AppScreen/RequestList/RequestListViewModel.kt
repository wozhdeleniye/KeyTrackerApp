package com.example.keytrackerapp.View.AppScreen.RequestList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmushits.Network.Auth.AuthRepository
import com.example.filmushits.Network.Network
import com.example.keytrackerapp.Network.Key.KeyRepository
import com.example.keytrackerapp.Network.Profile.ProfileRepository
import com.example.keytrackerapp.Network.Request.RequestRepository
import com.example.keytrackerapp.domain.Entities.Models.KeyListModel
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import com.example.keytrackerapp.domain.Entities.Models.ReqListModel
import com.example.keytrackerapp.domain.Entities.RequestBodies.CreateReqBody
import com.example.keytrackerapp.domain.UseCases.AuthUseCases.LoginUseCase
import com.example.keytrackerapp.domain.UseCases.KeysUseCases.GetKeysUseCase
import com.example.keytrackerapp.domain.UseCases.ProfileUseCases.ProfileGetUseCase
import com.example.keytrackerapp.domain.UseCases.RequestUseCases.CreateReqUseCase
import com.example.keytrackerapp.domain.UseCases.RequestUseCases.GetReqListUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RequestListViewModel: ViewModel() {
    fun getReqList(): Deferred<ReqListModel?> {
        return viewModelScope.async {
            val result = GetReqListUseCase(RequestRepository(Network.getInstance().getReqApi())).invoke()
            result.getOrNull()
        }
    }
    fun createReq(reqData: CreateReqBody) : Job {
        return viewModelScope.launch {
            val result = CreateReqUseCase(RequestRepository(Network.getInstance().getReqApi())).invoke(reqData)
            if (result.isSuccess) {

            }
            else cancel()
        }
    }
    fun getKeyList(): Deferred<KeyListModel?>{
        return viewModelScope.async {
            val result = GetKeysUseCase(KeyRepository(Network.getInstance().getKeyApi())).invoke()
            result.getOrNull()
        }
    }
}