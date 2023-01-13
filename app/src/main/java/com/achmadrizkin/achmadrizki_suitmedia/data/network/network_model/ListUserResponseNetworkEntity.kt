package com.achmadrizkin.achmadrizki_suitmedia.data.network.network_model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class ListUserResponseNetworkEntity(
    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("data")
    @Expose
    var data: List<UserResponseNetworkEntity>,
) {

}