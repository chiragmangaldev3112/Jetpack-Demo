package com.assignment.domain.model.common

import Destination
import com.assignment.R

// used to manage side menu and bottom meny
sealed class NavigationItem(val route: String) {

    sealed class MenuItem(
        route: String,
        val icon: Int,
        var title: Int
    ) : NavigationItem(
        route,
    ) {
        // home
        object Home : MenuItem(Destination.home, R.drawable.ic_baseline_home_24, R.string.home)

        // setting
        object Setting :
            MenuItem(Destination.setting, R.drawable.ic_baseline_settings_24, R.string.setting)

        // profile
        object Account :
            MenuItem(Destination.profile, R.drawable.ic_baseline_account_box_24, R.string.profile)
    }


}

// create a list of bottom menu and side menu
val screensInNav = listOf(
    NavigationItem.MenuItem.Home,
    NavigationItem.MenuItem.Setting,
    NavigationItem.MenuItem.Account
)


