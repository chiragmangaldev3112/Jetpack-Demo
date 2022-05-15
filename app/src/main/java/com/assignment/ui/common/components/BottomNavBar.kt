package com.assignment.ui.common.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.assignment.domain.model.common.screensInNav
import com.assignment.theme.AppTheme
import com.assignment.ui.common.provider.DashboardViewModelProvider
import com.assignment.ui.dashboard.viewmodel.DashBoardViewModel
import textResource

// used to manage bottom menu
@Composable
fun BottomNavigationBar(navController: NavController) {

    BottomNavigation(
        backgroundColor = AppTheme.colors.primary, // set background color of bottom menu
        contentColor = Color.White // set content color
    ) {
        // get view model of dashboard so can update title and side menu
        val dashBoardViewModel: DashBoardViewModel = DashboardViewModelProvider.dashBoardViewModel
        // get last entry
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        // get current route
        val currentRoute = navBackStackEntry?.destination?.route
        screensInNav.forEach { screen ->
            BottomNavigationItem(
                // set up icon
                icon = { Icon(painterResource(id = screen.icon), contentDescription = textResource(id = screen.title).toString()) },
                // set up label
                label = { Text(text = textResource(id = screen.title).toString()) },
                // set up selected color
                selectedContentColor = Color.White,
                // set up non selected color
                unselectedContentColor = Color.White.copy(0.4f),
                // show label if we want to hide , we can make it false
                alwaysShowLabel = true,
                // selection check on the basis of route
                selected = currentRoute == screen.route,
                onClick = {
                    // update dashboard view model
                    dashBoardViewModel.updateTitle(screen.route)
                    navController.navigate(screen.route) {
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
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
//    BottomNavigationBar()
}