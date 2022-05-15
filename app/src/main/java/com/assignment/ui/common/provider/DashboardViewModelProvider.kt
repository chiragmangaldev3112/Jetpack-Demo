package com.assignment.ui.common.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assignment.ui.dashboard.submodules.home.viewmodel.HomeViewModel
import com.assignment.ui.dashboard.submodules.profile.viewmodel.ProfileViewModel
import com.assignment.ui.dashboard.submodules.setting.viewmodel.SettingViewModel
import com.assignment.ui.dashboard.viewmodel.DashBoardViewModel
// used to get view model of dashboard or after Authentication
object DashboardViewModelProvider {


    val settingViewModel: SettingViewModel
        @Composable
        get() = LocalSettingViewModel.current
    val profileViewModel: ProfileViewModel
        @Composable
        get() = LocalProfileViewModel.current
    val homeViewModel: HomeViewModel
        @Composable
        get() = LocalHomeViewModel.current
    val dashBoardViewModel: DashBoardViewModel
        @Composable
        get() = LocalDashBoardViewModel.current
}

@Composable
fun ProvideDashboardViewModelProvider(content: @Composable () -> Unit) {
    val settingViewModel: SettingViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val dashBoardViewModel: DashBoardViewModel = viewModel()


    CompositionLocalProvider(
        LocalDashBoardViewModel provides dashBoardViewModel,
    ) {

        CompositionLocalProvider(
            LocalHomeViewModel provides homeViewModel,
        ) {

            CompositionLocalProvider(
                LocalSettingViewModel provides settingViewModel,
            ) {

                CompositionLocalProvider(
                    LocalProfileViewModel provides profileViewModel,
                ) {

                    content()

                }

            }

        }

    }
}

private val LocalSettingViewModel = staticCompositionLocalOf<SettingViewModel> {
    error("No SettingViewModel provided")
}

private val LocalProfileViewModel = staticCompositionLocalOf<ProfileViewModel> {
    error("No ProfileViewModel provided")
}

private val LocalHomeViewModel = staticCompositionLocalOf<HomeViewModel> {
    error("No HomeViewModel provided")
}
private val LocalDashBoardViewModel = staticCompositionLocalOf<DashBoardViewModel> {
    error("No DashBoardViewModel provided")
}