import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignment.domain.model.common.NavigationItem
import com.assignment.ui.auth.login.screen.LoginScreen


// used to set up auth flow
@Composable
fun NavGraph() {
    NavHost(Navigator.current, Destination.splash, route = Destination.authRoute) {
        composable(Destination.splash) { SplashScreen() }

        composable(Destination.login) {
            LoginScreen()
        }
        composable(Destination.dashboard) {
            DashBoarScreen()
        }


    }
}

// used to set up dashboard flow after login
@Composable
fun DashboardNavigationHost(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = NavigationItem.MenuItem.Home.route,
        route = Destination.dashboardRoute
    ) {


        composable(Destination.home) {
            HomeScreen()
        }

        composable(Destination.profile) {
            ProfileScreen()
        }
        composable(Destination.setting) {
            SettingScreen()
        }
    }
}