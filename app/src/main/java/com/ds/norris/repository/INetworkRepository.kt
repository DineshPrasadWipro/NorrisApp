package com.ds.norris.repository

import com.ds.norris.model.Norris
import com.ds.norris.model.NorrisList
import com.ds.norris.utils.NetworkResponse

interface INetworkRepository {

    suspend fun getRandomNorris(): NetworkResponse<Norris>

    suspend fun searchNorris(key: String): NetworkResponse<NorrisList>

    suspend fun getCategories(): NetworkResponse<List<String>>

    suspend fun searchNorrisByCategory(category: String): NetworkResponse<Norris>

}