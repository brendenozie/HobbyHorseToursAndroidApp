package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.composebase.R

@Composable
fun MyPhotosSection() {
    Text(
        text = "My Photography",
        style = typography.h6,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    val imageModifier = Modifier
        .padding(vertical = 8.dp, horizontal = 4.dp)
        .size(120.dp)
        .clip(RoundedCornerShape(8.dp))

    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.coffee),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.coffee),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.coffee),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.coffee),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.coffee),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.coffee),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}


