package com.ds.norris.viewmodel.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.norris.model.CustomNorrisList
import com.ds.norris.model.NorrisList
import com.ds.norris.repository.INetworkRepository
import com.ds.norris.utils.NetworkResponse
import kotlinx.coroutines.launch

class SearchJokesViewModel(private val networkRepository: INetworkRepository) : ViewModel() {
    private val _norrisList = MutableLiveData<CustomNorrisList?>()
    val norrisList: LiveData<CustomNorrisList?> = _norrisList

    val errorMessage = MutableLiveData<String>()

    fun searchNorris(key: String) {
        viewModelScope.launch {

            when (val response = networkRepository.searchNorris(key)) {
                is NetworkResponse.Success -> setValues(response.data)
                is NetworkResponse.Error -> errorMessage.value = response.message
                is NetworkResponse.NetworkException -> errorMessage.value =
                    response.exception.message
            }
        }
    }

    private fun setValues(norrisList: NorrisList) {
        _norrisList.value = CustomNorrisList(norrisList.total, norrisList.result)
    }
}