package com.ad.composemvvmstarter.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.composemvvmstarter.data.remote.DeviceListItem
import com.ad.composemvvmstarter.repository.BaseRepository
import com.ad.composemvvmstarter.utility.helper.NetworkGeneric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel(private val baseRepository: BaseRepository) : ViewModel() {

    private val _listOfDevicesFlow =
        MutableStateFlow<Resource<List<DeviceListItem>>?>(value = null)
    val listOfDevicesFlow: StateFlow<Resource<List<DeviceListItem>>?> = _listOfDevicesFlow

    fun callGetListOfDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            _listOfDevicesFlow.value = Resource.Loading()

            val response = baseRepository.callGetListOfDevices { error, message ->
                _listOfDevicesFlow.value = Resource.Error(error, message)
            } ?: return@launch

            val listItem = NetworkGeneric(response).getAsList(Array<DeviceListItem>::class.java)
                .takeIf { it.isNotEmpty() } ?: listOf()

            _listOfDevicesFlow.value = Resource.Success(listItem)
        }
    }
}