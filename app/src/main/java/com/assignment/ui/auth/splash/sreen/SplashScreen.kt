import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.assignment.R
import com.assignment.theme.AppTheme
import com.assignment.ui.auth.splash.viewmodel.SplashViewModel
import com.assignment.ui.common.provider.AuthViewModelProvider
import com.assignment.utils.Resource

@Composable
fun SplashScreen() {
    splashScreenContent()
}

@Preview(name = "Splash")
@Composable
fun splashScreenContent() {
    // get view model
    val splashViewModel: SplashViewModel = AuthViewModelProvider.splashViewModel
    // get screen name where to navigate
    val navigationScreenName = splashViewModel.navigationScreenName
    // get nav controller
    val navController = Navigator.current
    // when state changed or get to know whether authenticated or not , navigate to that screen as
    // per that
    navigateToNextScreen(navigationScreenName, navController)

    // render ui
    Scaffold {
        // random Ui
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // welcome text
            Text(
                text = textResource(id = R.string.welcome).toString(),
                style = AppTheme.typography.h2
            )


        }
    }

}


// used to navigate to next screen
private fun navigateToNextScreen(
    navigationScreenName: Resource<String>,
    navController: NavHostController
) {
    when (navigationScreenName) {
        is Resource.Error -> {
            // can show loader and any view if we want till then we can get status
        }
        Resource.Loading -> {
            // can show loader and any view if we want till then we can get status
        }
        is Resource.Success -> {
            // navigate to screen as per authetication
            navController.navigate(navigationScreenName.data) {
                popUpTo(Destination.splash) {
                    inclusive = true
                }
            }
        }
    }
}

