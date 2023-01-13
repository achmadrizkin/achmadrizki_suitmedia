package com.achmadrizkin.achmadrizki_suitmedia.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponse(
    var id: Int,
    var email: String,
    var first_name: String,
    var last_name: String,
    var avatar: String,
) {
}