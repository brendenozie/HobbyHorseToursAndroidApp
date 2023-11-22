package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.SubcomposeAsyncImageScope
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.Dimension
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.Shapes
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.hobbyTypography


@Composable
fun UpcomingPopularItem(
    item : ResultPopular?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .then(Modifier.clickable(onClick = onClick)),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

                if (item != null) {
                    CinemaxNetworkImage(
                        modifier = Modifier.fillMaxSize(),
                        model = item.document.img?.get(0)?.url,
                        contentDescription = item.document.title
                    )
                }
            RatingItem(
                rating = 4.5,
                modifier = Modifier
                    .padding(
                        top = Dimension.xs,
                        bottom = Dimension.xs,
                        end = Dimension.sm
                    )
                    .align(Alignment.TopEnd)
            )


            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.secondary.copy(alpha = 0.72f))
                    .padding(
                        start = Dimension.md,
                        bottom = Dimension.md,
                        end = Dimension.lg
                    )
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.BottomStart)
            ) {
                if (item != null) {
                    Text(
                        text = item.document.title,
                        style = hobbyTypography.subtitle1,
                        color = colorResource(id = R.color.white)
                        )
                    Spacer(modifier = Modifier.height(Dimension.xs))
                    Text(
                        text = item.document.description,
                        style = hobbyTypography.subtitle2,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = false)
@Composable
private fun UpcomingMovieItemPlaceholder(modifier: Modifier = Modifier) {
    UpcomingPopularItem(
        modifier = modifier,
        item = null,
        onClick = {},
    )
}

private val UpcomingMovieItemHeight = 154.dp

@Composable
fun CinemaxNetworkImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale
    ) { SubcomposeAsyncImageHandler() }
}

@Composable
fun CinemaxCardNetworkImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = Shapes.large
) {
    Card(modifier = modifier, shape = shape) {
        CinemaxNetworkImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale
        )
    }
}

@Composable
private fun SubcomposeAsyncImageScope.SubcomposeAsyncImageHandler() {
    when (painter.state) {
        is AsyncImagePainter.State.Loading -> {}
        is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
        AsyncImagePainter.State.Empty, is AsyncImagePainter.State.Error -> Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.onSurface)
        )
    }
}
