package com.assignment.domain.repository.users

import com.assignment.data.network.model.users.UsersDto
import com.assignment.data.network.service.AppService
import com.assignment.error.ErrorHAndler
import com.assignment.utils.Either
import com.assignment.utils.failure
import com.assignment.utils.success


class UsersRepositoryImpl(
    private val service: AppService,
) : UsersRepository {

    // help to know the logs
    companion object {
        private const val TAG = "UsersRepository"
    }

    // used to get user listing from api
    override suspend fun searchUsers(pageIndex: Int): Either<ErrorHAndler, UsersDto> {
        return try {
            // get data from api and convert to model
            val result = service.searchUsers(pageIndex).asDomainModel()
            // send data to Ui
            success(result)
        } catch (e: Exception) {
            // handle exception and can handle 401 authentication from here also
            failure(ErrorHAndler.UnexpectedErrorHAndler)
        }
    }
}