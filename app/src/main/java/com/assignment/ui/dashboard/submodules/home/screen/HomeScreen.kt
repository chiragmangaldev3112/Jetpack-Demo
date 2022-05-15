import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.assignment.R
import com.assignment.data.network.model.users.UserInfoDto
import com.assignment.theme.AppTheme
import com.assignment.ui.common.components.ErrorTextContent
import com.assignment.ui.common.components.LoadingPlaceholder
import com.assignment.ui.common.components.PhotoPagerScreen
import com.assignment.ui.common.provider.DashboardViewModelProvider
import com.assignment.ui.dashboard.submodules.home.viewmodel.HomeViewModel
import com.assignment.utils.ConnectionState
import com.assignment.utils.Resource
import com.assignment.utils.connectivityState
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
fun HomeScreen() {

    HomeScreenContent()

}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent() {
    // used to get connectivity state
    val connection by connectivityState()
    // used to get home view model
    val homeViewModel: HomeViewModel = DashboardViewModelProvider.homeViewModel
    // used to get users
    var users = homeViewModel.users
    // used to know internet connection
    val isConnected = connection === ConnectionState.Available
     // if list empty and network available
    if (isConnected && users == Resource.Empty) {
        // get data from apu
        homeViewModel.searchUser()
    }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

         // show Ui as per internet and user listing
            when (users) {
                is Resource.Empty -> {
                    if(!isConnected)
                    ErrorTextContent(id = R.string.no_internet_found)
                }
                is Resource.Error -> {
                    ErrorTextContent(id = R.string.unexpected_error)
                    // handle in case of error
                }
                Resource.Loading -> {
                    if(isConnected)
                    LoadingPlaceholder()
                }
                is Resource.Success -> {
                    users.data.data?.let { it1 ->
                        Box {
                            Column {

                                CrousalViewContent(users = it1, modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth())

                                GridViewContent(users = it1,  modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth())

                            }
                        }
                    }?.run {
                        ErrorTextContent(id = R.string.no_user_found)
                    }

                }
            }


        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CrousalViewContent(users :List<UserInfoDto>,modifier: Modifier){
    Box(
        modifier = modifier
    ) {
        PhotoPagerScreen(users, 0)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridViewContent(users :List<UserInfoDto>,modifier: Modifier){
    Box(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            items(users.size) {
                UserImage(
                    url = users[it].avatar,
                    modifier = Modifier.padding(AppTheme.dimens.size_2)
                )
            }
        }
    }
}