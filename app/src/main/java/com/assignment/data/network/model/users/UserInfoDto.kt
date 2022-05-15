package com.assignment.data.network.model.users

import com.google.gson.annotations.SerializedName

// used to get the user data
data class UserInfoDto(
    @SerializedName("last_name")
    val lastName: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("avatar")
    val avatar: String = "",
    @SerializedName("first_name")
    val firstName: String = "",
    @SerializedName("email")
    val email: String = ""
) {
    // help to create model
    fun asDomainModel() = UserInfoDto(
        lastName,
        id,
        avatar,
        firstName,
        email
    )
}