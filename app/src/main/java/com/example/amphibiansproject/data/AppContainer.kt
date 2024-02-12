package com.example.amphibiansproject.data

import com.example.amphibiansproject.network.AmphibianApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val amphibiansRepository:AmphibiansRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
    private val retrofitService:AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }
    override val amphibiansRepository:AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(retrofitService)
    }
}