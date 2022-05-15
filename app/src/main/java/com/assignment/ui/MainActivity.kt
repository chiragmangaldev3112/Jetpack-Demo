package com.assignment.ui

import NavGraph
import ProvideNavHostController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.assignment.theme.AppTheme
import com.assignment.ui.common.provider.ProvideAuthMultiViewModel
import com.assignment.ui.common.provider.ProvideDashboardViewModelProvider
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentApp(
                backDispatcher = onBackPressedDispatcher
            )
        }
    }
}

@Composable
fun AssignmentApp(
    backDispatcher: OnBackPressedDispatcher
) {
    // app theme
    AppTheme {
        ProvideWindowInsets {
            // auth view model
            ProvideAuthMultiViewModel {
                // dashboard view model
                ProvideDashboardViewModelProvider {
                    // nav controller provider
                    ProvideNavHostController {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            NavGraph()
                        }
                    }
                }
            }
        }
    }
}
