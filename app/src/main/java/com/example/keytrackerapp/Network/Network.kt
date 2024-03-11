package com.example.filmushits.Network

import androidx.lifecycle.ViewModel
import com.example.filmushits.Network.Auth.AuthApi
import com.example.keytrackerapp.Network.Key.KeyApi
import com.example.keytrackerapp.Network.Profile.ProfileApi
import com.example.keytrackerapp.Network.Request.RequestApi
import com.example.keytrackerapp.Network.Transfer.TransferApi
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
class Network {
    private val BASE_URL = "http://158.160.147.51:8181/"
    private lateinit var retrofit: Retrofit
    private lateinit var tokenManager: TokenManager

    fun initialize(){
        this.retrofit = getRetrofit()
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getHttpClient())
            .build()
    }
    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
            addInterceptor(MyInterceptor())
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }
        return client.build()
    }
    fun setTokenManager(tokenManager: TokenManager){
        this.tokenManager = tokenManager
    }
    fun getAuthApi(): AuthApi.AuthApi = retrofit.create(AuthApi.AuthApi::class.java)
    fun getProfileApi(): ProfileApi = retrofit.create(ProfileApi::class.java)
    fun getReqApi(): RequestApi = retrofit.create(RequestApi::class.java)
    fun getKeyApi(): KeyApi = retrofit.create(KeyApi::class.java)
    fun getTransferApi(): TransferApi = retrofit.create(TransferApi::class.java)

    companion object {
        private var instance: Network? = null

        fun getInstance(): Network{
            return instance ?: synchronized(this){
                instance ?: Network().also{ instance = it}
            }
        }

    }
    fun getTokenManager(): TokenManager {
        return this.tokenManager
    }
}

