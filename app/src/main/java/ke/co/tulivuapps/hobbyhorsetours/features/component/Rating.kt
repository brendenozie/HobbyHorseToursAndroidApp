package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.AppTypography
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.Dimension

@Composable
fun RatingItem(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.secondary.copy(alpha = RatingItemContainerColorAlpha),
        contentColor = MaterialTheme.colors.onSecondary
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = Dimension.sm,
                vertical = Dimension.xs
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                Modifier.size(RatingIconSize)
            )
            Spacer(modifier = Modifier.width(Dimension.xs))
            Text(
                text = "4.5",
                style = AppTypography.body1
            )
        }
    }
}

private val RatingIconSize = 20.dp
private const val RatingItemContainerColorAlpha = 0.72f
private const val RatingNotRatedValue = 0.0
