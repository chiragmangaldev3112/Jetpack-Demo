package com.assignment.ui.common.provider


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assignment.ui.auth.login.viewmodel.LoginViewModel
import com.assignment.ui.auth.splash.viewmodel.SplashViewModel

// used to get all view model of authentication flow
object AuthViewModelProvider {
    val splashViewModel: SplashViewModel
        @Composable
        get() = LocalSplashViewModelViewModel.current

    val loginViewModel: LoginViewModel
        @Composable
        get() = LocalLoginViewModelViewModel.current


}

@Composable
fun ProvideAuthMultiViewModel(content: @Composable () -> Unit) {
    val splashViewModel: SplashViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()


    CompositionLocalProvider(
        LocalSplashViewModelViewModel provides splashViewModel,
    ) {
        CompositionLocalProvider(
            LocalLoginViewModelViewModel provides loginViewModel,
        ) {

            content()

        }
    }
}

private val LocalSplashViewModelViewModel = staticCompositionLocalOf<SplashViewModel> {
    error("No SplashViewModel provided")
}

private val LocalLoginViewModelViewModel = staticCompositionLocalOf<LoginViewModel> {
    error("No LoginViewModel provided")
}

