package com.example.filmushits.Network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request().newBuilder().apply {
            addHeader("accept", "application/json")
            addHeader("content-Type", "application/x-www-form-urlencoded")
            addHeader("Authorization", "Bearer ${Network.getInstance().getTokenManager().getToken()}")
        }.build()

        var response: Response? = null

        return try{
            response = chain.proceed(request)
            Log.d("123456", response.message)
            response
        } catch (e: Exception){
            response?.close()
            chain.proceed(request)
        }
    }
}