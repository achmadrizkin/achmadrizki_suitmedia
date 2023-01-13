package com.achmadrizkin.achmadrizki_suitmedia.data.network.network_model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponseNetworkEntity(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("email")
    @Expose
    var email: String,

    @SerializedName("first_name")
    @Expose
    var first_name: String,

    @SerializedName("last_name")
    @Expose
    var last_name: String,

    @SerializedName("avatar")
    @Expose
    var avatar: String,
) {
}