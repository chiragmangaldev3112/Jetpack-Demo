package com.assignment.ui.dashboard.submodules.setting.viewmodel

import androidx.lifecycle.ViewModel
import com.assignment.data.datastore.AppDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val dataStore: AppDataStore
) : ViewModel()