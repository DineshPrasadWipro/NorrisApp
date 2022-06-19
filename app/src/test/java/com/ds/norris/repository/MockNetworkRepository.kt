package com.ds.norris.repository

import com.ds.norris.model.Norris
import com.ds.norris.model.NorrisList
import com.ds.norris.utils.NetworkResponse

class MockNetworkRepository : INetworkRepository {

    private val categoryList: List<String> = listOf("animal")
    private val norris: Norris = setNorris()
    private val norrisList: NorrisList = setNorrisList()

    private fun setNorris(): Norris {
        return Norris("", "", "112", emptyList(), "", "", "")

    }

    private fun setNorrisList(): NorrisList {
        return NorrisList("", listOf(setNorris()))

    }

    override suspend fun getRandomNorris(): NetworkResponse<Norris> {
        return NetworkResponse.Success(norris)
    }

    override suspend fun searchNorris(key: String): NetworkResponse<NorrisList> {
        return NetworkResponse.Success(norrisList)
    }

    override suspend fun getCategories(): NetworkResponse<List<String>> {
        return NetworkResponse.Success(categoryList)
    }

    override suspend fun searchNorrisByCategory(category: String): NetworkResponse<Norris> {
        return NetworkResponse.Success(norris)
    }

}