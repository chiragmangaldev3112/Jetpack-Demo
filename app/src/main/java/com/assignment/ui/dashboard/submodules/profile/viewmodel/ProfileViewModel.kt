package com.assignment.ui.dashboard.submodules.profile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.data.datastore.AppDataStore
import com.assignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStore: AppDataStore
) : ViewModel() {

    // used to manage authentication
    var authenticated by mutableStateOf<Resource<Boolean>>(Resource.Empty)
        private set

    // used to clear session
    fun clearSession() {
        authenticated = Resource.Loading
        viewModelScope.launch {
            dataStore.storeAuthenticatedState(false)
            authenticated = Resource.Success(false)
        }
    }

}