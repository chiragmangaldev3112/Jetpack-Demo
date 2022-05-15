import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.assignment.ui.common.components.BottomNavigationBar
import com.assignment.ui.common.components.TopBar
import com.assignment.ui.common.provider.DashboardViewModelProvider
import com.assignment.ui.dashboard.viewmodel.DashBoardViewModel
import kotlinx.coroutines.launch

@Composable
fun DashBoarScreen() {
    // used to get scaffold state
    val scaffoldState = rememberScaffoldState()
    // use dto get courotine scope
    val scope = rememberCoroutineScope()
    // used to get nav controller
    val navController = rememberNavController()
    // used to get dashboard view model
    val dashBoardViewModel: DashBoardViewModel = DashboardViewModelProvider.dashBoardViewModel

    Scaffold(
        scaffoldState = scaffoldState, // scaffold state
        topBar = { TopBar(textResource(id = dashBoardViewModel.screenTitle).toString()){
            scope.launch {
                if( scaffoldState.drawerState.isClosed){
                    scaffoldState.drawerState.open()
                }
            }

        } }, // top bar
        bottomBar = { BottomNavigationBar(navController) }, // bottom bar
        drawerContent = { // side menue
            Drawer { route ->
                // on selection of side menu update view model of dashboard
                dashBoardViewModel.updateTitle(route)
                // close drawer
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                // navigat to that page which selected
                navController.navigate(route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        },

        ) {

        // add padding so that view doesnt get cut at the bottom
        Box(modifier = Modifier.padding(it)) {
            DashboardNavigationHost(navController)
        }

    }
}

