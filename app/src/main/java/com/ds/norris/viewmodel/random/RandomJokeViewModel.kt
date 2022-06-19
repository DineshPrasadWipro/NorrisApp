package com.ds.norris.viewmodel.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.norris.model.CustomNorris
import com.ds.norris.model.Norris
import com.ds.norris.repository.INetworkRepository
import com.ds.norris.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RandomJokeViewModel(private val networkRepository: INetworkRepository) : ViewModel() {

    private val _randomNorris = MutableLiveData<CustomNorris>()
    val randomNorris: LiveData<CustomNorris> = _randomNorris

    val errorMessage = MutableLiveData<String>()

    fun getRandomNorris() {
        viewModelScope.launch {
            val response = networkRepository.getRandomNorris()

            withContext(Dispatchers.Main) {
                when (response) {
                    is NetworkResponse.Success -> setValues(response.data)
                    is NetworkResponse.Error -> errorMessage.value = response.message
                    is NetworkResponse.NetworkException -> errorMessage.value =
                        response.exception.message
                }
            }
        }

    }

    private fun setValues(norris: Norris) {
        val customRandomNorris = CustomNorris(
            norris.createdAt, norris.iconUrl, norris.id, norris.categories,
            norris.updatedAt, norris.value, norris.url
        )
        _randomNorris.value = customRandomNorris
    }
}
