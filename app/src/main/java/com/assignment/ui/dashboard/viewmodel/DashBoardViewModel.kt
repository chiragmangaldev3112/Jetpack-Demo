package com.assignment.ui.dashboard.viewmodel

import Destination
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.assignment.R
import com.assignment.data.datastore.AppDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val dataStore: AppDataStore
) : ViewModel() {
    // used to get screen title
    var screenTitle by mutableStateOf<Int>(R.string.home)
        private set

    // used to update screen title
    fun updateTitle(screenRoute: String) {

        when (screenRoute) {
            Destination.home -> {
                screenTitle = R.string.home
            }
            Destination.profile -> {
                screenTitle = R.string.profile
            }
            Destination.setting -> {
                screenTitle = R.string.setting
            }
        }

    }
}