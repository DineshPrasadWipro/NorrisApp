package com.ds.norris.repository

import com.ds.norris.model.Norris
import com.ds.norris.model.NorrisList
import com.ds.norris.network.NetworkInterface
import com.ds.norris.utils.NetworkResponse

open class NetworkRepository(private val networkInterface: NetworkInterface) : INetworkRepository {
    override suspend fun getRandomNorris(): NetworkResponse<Norris> {

        return try {
            val response = networkInterface.getRandomNorris()
            if (response.isSuccessful) {
                NetworkResponse.Success(data = response.body() as Norris)
            } else {
                NetworkResponse.Error(message = response.message())
            }
        } catch (exception: Exception) {
            NetworkResponse.NetworkException(exception = exception)
        }
    }

    override suspend fun searchNorris(key: String): NetworkResponse<NorrisList> {
        return try {
            val response = networkInterface.searchNorris(key)
            if (response.isSuccessful) {
                NetworkResponse.Success(data = response.body() as NorrisList)
            } else {
                NetworkResponse.Error(message = response.message().toString())
            }
        } catch (exception: Exception) {
            NetworkResponse.NetworkException(exception = exception)
        }
    }

    override suspend fun getCategories(): NetworkResponse<List<String>> {
        return try {
            val response = networkInterface.getCategories()
            if (response.isSuccessful) {
                NetworkResponse.Success(data = response.body() as List<String>)
            } else {
                NetworkResponse.Error(message = response.message().toString())
            }
        } catch (exception: Exception) {
            NetworkResponse.NetworkException(exception = exception)
        }
    }

    override suspend fun searchNorrisByCategory(category: String): NetworkResponse<Norris> {
        return try {
            val response = networkInterface.searchNorrisbyCategory(category)
            if (response.isSuccessful) {
                NetworkResponse.Success(data = response.body() as Norris)
            } else {
                NetworkResponse.Error(message = response.message().toString())
            }
        } catch (exception: Exception) {
            NetworkResponse.NetworkException(exception = exception)
        }
    }

}