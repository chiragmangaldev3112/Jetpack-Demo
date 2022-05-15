package com.assignment.ui.common.components


import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.assignment.R
import com.assignment.theme.AppTheme
import com.assignment.theme.whiteColor
import androidx.compose.material.Icon

// used to show top bar and can add burger menu here
@Composable
fun TopBar(name: String,onHamBurgerClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = name, style = AppTheme.typography.h2) },
        navigationIcon = {
           IconButton(onClick = {
               onHamBurgerClicked()

           }) {
               Icon( Icons.Filled.Menu,"")
           }
        },
        backgroundColor = AppTheme.colors.primaryVariant,
        contentColor = whiteColor
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
//    TopBar("Home")
}