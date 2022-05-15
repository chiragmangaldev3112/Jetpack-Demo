package com.assignment.domain.repository.users

import com.assignment.data.network.model.users.UsersDto
import com.assignment.error.ErrorHAndler
import com.assignment.utils.Either

// used to manage user repo
interface UsersRepository {
    // used to get user listing
    suspend fun searchUsers(
        pageIndex: Int
    ): Either<ErrorHAndler, UsersDto>
}