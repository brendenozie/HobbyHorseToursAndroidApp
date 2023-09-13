package ke.co.tulivuapps.hobbyhorsetours.features.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomScrollingContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(8.dp)
    ) {
//        SocialRow()
//        Text(
//            text = "About Me",
//            style = typography.h6,
//            color = MaterialTheme.colors.primary,
//            modifier = Modifier.padding(start = 8.dp, top = 12.dp)
//        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
//        Text(
//            text = "About me",
//            style = typography.body1,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//        )
//        InterestsSection()
//        MyPhotosSection()
//        Text(
//            text = "About Project",
//            style = typography.h6,
//            color = MaterialTheme.colors.primary,
//            modifier = Modifier.padding(start = 8.dp, top = 16.dp)
//        )
//        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
//        Text(
//            text = "About Project",
//            style = typography.body1,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//        )
        MoreInfoSection()
    }
}
