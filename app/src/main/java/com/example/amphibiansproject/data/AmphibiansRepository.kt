package com.example.amphibiansproject.data

import com.example.amphibiansproject.model.Amphibian
import com.example.amphibiansproject.network.AmphibianApiService

interface AmphibiansRepository  {
    suspend fun getAmphibiansInfo(): List<Amphibian>
}
class NetworkAmphibiansRepository(
    private val amphibianApiService: AmphibianApiService
):AmphibiansRepository {
    override suspend fun getAmphibiansInfo(): List<Amphibian> {
        return amphibianApiService.getInfos()
    }
}