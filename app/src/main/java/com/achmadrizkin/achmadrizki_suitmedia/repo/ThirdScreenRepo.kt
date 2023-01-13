package com.achmadrizkin.achmadrizki_suitmedia.repo

import com.achmadrizkin.achmadrizki_suitmedia.data.model.UserResponse
import com.achmadrizkin.achmadrizki_suitmedia.data.network.RetrofitService
import com.achmadrizkin.achmadrizki_suitmedia.data.network.network_mapper.UserResponseNetworkMapper
import com.achmadrizkin.achmadrizki_suitmedia.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ThirdScreenRepo @Inject constructor(
    private val retrofitService: RetrofitService,
    private val userResponseNetworkMapper: UserResponseNetworkMapper
) {
    suspend fun getAllUserFromAPI(page: Int): Flow<DataState<List<UserResponse>>> = flow {
        emit(DataState.Loading)

        try {
            val getAllUser = retrofitService.getAllJobs(page)
            emit(DataState.Success(userResponseNetworkMapper.mapFromEntityList(getAllUser.data)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}