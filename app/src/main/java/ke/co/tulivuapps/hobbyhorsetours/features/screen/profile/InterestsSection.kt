package ke.co.tulivuapps.hobbyhorsetours.features.screen.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.features.component.InterestTag

@Composable
fun InterestsSection() {
    Text(
        text = "My Interests",
        style = typography.h6,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
        InterestTag("Android")
        InterestTag("Compose")
        InterestTag("Flutter")
        InterestTag("SwiftUI")
    }
    Row(modifier = Modifier.padding(start = 8.dp)) {
        InterestTag("Video games")
        InterestTag("Podcasts")
        InterestTag("Basketball")
    }
}
