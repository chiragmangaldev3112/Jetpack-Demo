import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.assignment.domain.model.common.screensInNav
import com.assignment.theme.AppTheme
import com.assignment.theme.blackColor
import com.assignment.ui.common.provider.DashboardViewModelProvider
import com.assignment.ui.dashboard.viewmodel.DashBoardViewModel

// help to manage side menu
@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
// used to get view model of dashboard
    val dashBoardViewModel: DashBoardViewModel = DashboardViewModelProvider.dashBoardViewModel
    // create Ui of drawer
    Column(
        modifier
            .fillMaxSize()
            .background(
                // create gradient of side menu
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppTheme.colors.primary,
                        AppTheme.colors.primaryVariant,
                    )
                )
            )

    ) {
// loop side menu
        screensInNav.forEach { screen ->
            Card(
                modifier = Modifier
                    .padding(AppTheme.dimens.size_1)
                    .fillMaxWidth()
                    .clickable {
                        // on click of menue
                        onDestinationClicked(screen.route)
                    },
                elevation = 0.dp,
                backgroundColor = if (dashBoardViewModel.screenTitle == screen.title) blackColor.copy(
                    alpha = 0.4f
                ) else Color.Transparent
            ) {
                // show title of side menu
                Text(
                    text = textResource(id = screen.title).toString(),
                    style = AppTheme.typography.h2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimens.size_1)

                )
            }

        }
    }
}
