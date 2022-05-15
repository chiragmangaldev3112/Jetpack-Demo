package com.assignment.ui.dashboard.submodules.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.data.datastore.AppDataStore
import com.assignment.data.network.model.users.UsersDto
import com.assignment.domain.repository.users.UsersRepository
import com.assignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStore: AppDataStore,
    private val usersRepository: UsersRepository
) : ViewModel() {

    // used to get user listing
    var users by mutableStateOf<Resource<UsersDto>>(Resource.Empty)
        private set

   // used to search user
    fun searchUser() {
        viewModelScope.launch {
            users = Resource.Loading
            // get data from api
            val result = usersRepository.searchUsers(1)
            // send data to Ui
            result.fold(
                { failure ->
                    users = Resource.Error(failure)
                },
                { data ->
                    users = Resource.Success(data)
                }
            )
        }
    }
}