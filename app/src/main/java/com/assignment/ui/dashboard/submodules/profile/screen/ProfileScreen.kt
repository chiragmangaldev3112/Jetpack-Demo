import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.assignment.R
import com.assignment.theme.AppTheme
import com.assignment.ui.common.components.LoadingPlaceholder
import com.assignment.ui.common.provider.DashboardViewModelProvider
import com.assignment.ui.dashboard.submodules.profile.viewmodel.ProfileViewModel
import com.assignment.utils.Resource


@Composable
fun ProfileScreen() {
    // used to get profile view model
    val profileViewModel: ProfileViewModel = DashboardViewModelProvider.profileViewModel
    // used to get nav controller
    val navController = Navigator.current
    // used to manage logout content
    LogoutContent(profileViewModel = profileViewModel, navController = navController)
    // used to show profile screen content
    ProfileScreenContent(profileViewModel = profileViewModel)

}

@Composable
fun ProfileScreenContent(profileViewModel: ProfileViewModel) {
    // random UI
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Button(onClick = {
                // clear session on logout , for now no internet check there
                profileViewModel.clearSession()
            }) {
                Text(
                    text = textResource(id = R.string.logout).toString(),
                    style = AppTheme.typography.h2
                )
            }


        }
    }

}

@Composable
fun LogoutContent(profileViewModel: ProfileViewModel, navController: NavController) {
    when (val authenticated = profileViewModel.authenticated) {
        is Resource.Error -> {

            // handle in case of error
        }
        Resource.Loading -> {
            LoadingPlaceholder()

        }
        is Resource.Success -> {
            // navigate to login screen

            if (!authenticated.data) {
                // Todo need to handle it properly
                val activity = LocalContext.current as Activity
                activity.finish()
                activity.startActivity(activity.intent)


            }
        }
    }

}