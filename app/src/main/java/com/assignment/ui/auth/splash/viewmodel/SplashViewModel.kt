package com.assignment.ui.auth.splash.viewmodel

import Destination
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
class SplashViewModel @Inject constructor(
    private val dataStore: AppDataStore
) : ViewModel() {
    // help to know where to navigate
    var navigationScreenName by mutableStateOf<Resource<String>>(Resource.Loading)
        private set


    init {
        // when view model created start timer
        startCountDown()
    }

    private fun startCountDown() {
     // after some time check authentication
        Timer(AppConstants.AUTHENTICATED_TIMER_TAG, false).schedule(timerTask {
            viewModelScope.launch {
                // update value to view model so Ui can update and navigate to screen as per required
                navigationScreenName =
                    Resource.Success(if (dataStore.readAuthenticatedState()) Destination.dashboard else Destination.login)
            }
        }, AppConstants.SPLASH_TIMER_VALUE)

    }

    // clear timer
    override fun onCleared() {
        super.onCleared()
        Timer(AppConstants.AUTHENTICATED_TIMER_TAG).cancel()
    }

}