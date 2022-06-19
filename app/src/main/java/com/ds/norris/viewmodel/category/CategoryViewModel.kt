package com.ds.norris.viewmodel.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.norris.model.CustomNorris
import com.ds.norris.model.Norris
import com.ds.norris.repository.INetworkRepository
import com.ds.norris.utils.NetworkResponse
import kotlinx.coroutines.launch

class CategoryViewModel(private val networkRepository: INetworkRepository) : ViewModel() {

    private val _categoryList = MutableLiveData<List<String>?>()
    val categoryList: LiveData<List<String>?> = _categoryList

    private val _norris = MutableLiveData<CustomNorris>()
    val norris: MutableLiveData<CustomNorris> = _norris

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getCategories() {
        viewModelScope.launch {

            when (val response = networkRepository.getCategories()) {
                is NetworkResponse.Success -> setValues(response.data)
                is NetworkResponse.Error -> _errorMessage.value = response.message
                is NetworkResponse.NetworkException -> _errorMessage.value =
                    response.exception.message
            }
        }
    }

    fun searchNorrisByCategory(key: String) {
        viewModelScope.launch {

            when (val response = networkRepository.searchNorrisByCategory(key)) {
                is NetworkResponse.Success -> setNorrisValue(response.data)
                is NetworkResponse.Error -> _errorMessage.value = response.message
                is NetworkResponse.NetworkException -> _errorMessage.value =
                    response.exception.message
            }
        }
    }


    private fun setNorrisValue(norris: Norris) {
        val customNorris = CustomNorris(
            norris.createdAt, norris.iconUrl, norris.id, norris.categories,
            norris.updatedAt, norris.value, norris.url
        )
        _norris.value = customNorris
    }

    private fun setValues(categoryList: List<String>) {
        _categoryList.value = categoryList
    }

}