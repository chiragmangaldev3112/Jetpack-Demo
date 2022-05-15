package com.assignment.data.network.service

import com.assignment.data.network.constant.EndPoints
import com.assignment.data.network.model.users.UsersDto
import retrofit2.http.GET
import retrofit2.http.Query

// help to connect to server and get response from it
interface AppService {

    @GET(EndPoints.USERS)
    suspend fun searchUsers(
        @Query("page") pageIndex: Int
    ): UsersDto
}