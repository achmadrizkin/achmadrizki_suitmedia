package com.achmadrizkin.achmadrizki_suitmedia.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achmadrizkin.achmadrizki_suitmedia.data.model.UserResponse
import com.achmadrizkin.achmadrizki_suitmedia.repo.ThirdScreenRepo
import com.achmadrizkin.achmadrizki_suitmedia.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdScreenViewModel @Inject constructor(
    private val thirdScreenRepo: ThirdScreenRepo,
): ViewModel() {
    private val _dataStateGetAllUserFromAPI: MutableLiveData<DataState<List<UserResponse>>> = MutableLiveData()

    // this is like observable in java
    val getAllUserFromAPI: LiveData<DataState<List<UserResponse>>> = _dataStateGetAllUserFromAPI

    //
    fun setStateEventGetAllUserFromApiCall(userStateEvent: UserStateEvent, page: Int) {
        viewModelScope.launch {
            when(userStateEvent) {
                is UserStateEvent.GetAllUserEvents -> {
                    thirdScreenRepo.getAllUserFromAPI(page).onEach { dataState ->
                        _dataStateGetAllUserFromAPI.value = dataState
                    }.launchIn(viewModelScope)
                }

                is UserStateEvent.None -> {

                }
            }
        }
    }

    fun addDataToList(listUserResponse: MutableList<UserResponse>, listUser: List<UserResponse>): MutableList<UserResponse> {
        //
        for (element in listUser) {
            listUserResponse.add(element)
        }

        return listUserResponse
    }
}

sealed class UserStateEvent {
    object GetAllUserEvents : UserStateEvent()

    object None : UserStateEvent()
}