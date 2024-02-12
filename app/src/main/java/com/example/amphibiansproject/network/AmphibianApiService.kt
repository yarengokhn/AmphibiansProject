package com.example.amphibiansproject.network

import com.example.amphibiansproject.model.Amphibian
import retrofit2.http.GET

    interface AmphibianApiService {
        @GET("amphibians")
        suspend fun getInfos() :List <Amphibian>
}