package com.achmadrizkin.achmadrizki_suitmedia.data.network.network_mapper

import com.achmadrizkin.achmadrizki_suitmedia.data.model.UserResponse
import com.achmadrizkin.achmadrizki_suitmedia.data.network.network_model.UserResponseNetworkEntity
import com.achmadrizkin.achmadrizki_suitmedia.utils.EntityMapper
import javax.inject.Inject

class UserResponseNetworkMapper @Inject constructor():
    EntityMapper<UserResponseNetworkEntity, UserResponse> {
    override fun mapFromEntity(entity: UserResponseNetworkEntity): UserResponse {
        return UserResponse(
            id = entity.id,
            email = entity.email,
            last_name = entity.last_name,
            first_name = entity.first_name,
            avatar = entity.avatar
        )
    }

    override fun mapToEntity(domainModel: UserResponse): UserResponseNetworkEntity {
        return UserResponseNetworkEntity(
            id = domainModel.id,
            email = domainModel.email,
            last_name = domainModel.last_name,
            first_name = domainModel.first_name,
            avatar = domainModel.avatar
        )
    }

    // converting map to object
    fun mapFromEntityList(entity: List<UserResponseNetworkEntity>): List<UserResponse> {
        return entity.map { mapFromEntity(it) }
    }
}