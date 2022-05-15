package com.assignment.ui.auth.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.data.datastore.AppDataStore
import com.assignment.utils.AppConstants
import com.assignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: AppDataStore
) : ViewModel() {
    // used to know whether user got autheticated or not
    var authenticated by mutableStateOf<Resource<Boolean>>(Resource.Empty)
        private set
    // start authentication
    fun startAuthentication() {
        authenticated = Resource.Loading
        Timer(AppConstants.LOGIN_TIMER_TAG, false).schedule(timerTask {
            viewModelScope.launch {
                // update data to data store
                dataStore.storeAuthenticatedState(true)
                // send data to Ui
                authenticated = Resource.Success(true)
            }
        }, AppConstants.LOGIN_TIMER_VALUE)
    }

    // clear timer
    override fun onCleared() {
        super.onCleared()
        Timer(AppConstants.LOGIN_TIMER_TAG).cancel()
    }
}