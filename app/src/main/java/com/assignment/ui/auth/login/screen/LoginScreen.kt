package com.assignment.ui.auth.login.screen

import Destination
import Navigator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.assignment.R
import com.assignment.theme.AppTheme
import com.assignment.ui.auth.login.viewmodel.LoginViewModel
import com.assignment.ui.common.components.LoadingPlaceholder
import com.assignment.ui.common.provider.AuthViewModelProvider
import com.assignment.utils.Resource
import textResource

@Composable
fun LoginScreen() {
    LoginScreenContent()
}

@Composable
fun LoginScreenContent() {
    // used to get view model
    val loginViewModel: LoginViewModel = AuthViewModelProvider.loginViewModel
    // used to know whether user successfully logged in or not
    val authenticated = loginViewModel.authenticated
    // used to get nav controller
    val navController = Navigator.current
    // used to navigate to next screen after successfully logged in
    navigateToNextScreen(authenticated = authenticated, navController = navController)
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = {
                // start authentication , No internet check is being added as for now
                loginViewModel.startAuthentication()
            }) {
                Text(
                    text = textResource(id = R.string.login).toString(),
                    style = AppTheme.typography.h2
                )
            }


        }
    }
}

// used to navigate to next screen
@Composable
private fun navigateToNextScreen(
    authenticated: Resource<Boolean>,
    navController: NavHostController
) {
    when (authenticated) {
        is Resource.Error -> {
            // show error message if error
        }
        Resource.Loading -> {
            LoadingPlaceholder()
        }
        is Resource.Success -> {
            // navigate to screen as per authentication
            navController.navigate(Destination.dashboard) {
                popUpTo(Destination.login) {
                    inclusive = true
                }
            }
        }
    }
}

