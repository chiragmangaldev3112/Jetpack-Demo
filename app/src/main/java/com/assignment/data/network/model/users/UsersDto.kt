package com.assignment.data.network.model.users

import com.google.gson.annotations.SerializedName

// used to traverse the list of users
data class UsersDto(
    @SerializedName("per_page")
    val perPage: Int = 0,
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("data")
    val data: List<UserInfoDto>?,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0
) {

    // help to create model
    fun asDomainModel() = UsersDto(
        perPage,
        total,
        data?.map { it.asDomainModel() },
        page,
        totalPages
    )

}