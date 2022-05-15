import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.assignment.R
import com.assignment.theme.AppTheme
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

// used to show images from url
@Composable
fun UserImage(
    url: String,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 1f,
) {
    val imagePainter = rememberCoilPainter(url)


    Box(
        modifier
            .clip(MaterialTheme.shapes.medium)
            .aspectRatio(aspectRatio)
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.08f))
    ) {
        Image(
            painter = imagePainter,
            contentDescription = url,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        when (imagePainter.loadState) {
            is ImageLoadState.Success -> {
                // Remove placeholder
            }
            else -> {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = url,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.14f),
                    modifier = Modifier
                        .size(AppTheme.dimens.size_6)
                        .align(Alignment.Center),
                )
            }
        }
    }
}