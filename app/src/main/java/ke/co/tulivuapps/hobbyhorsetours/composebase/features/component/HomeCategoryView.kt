package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.composebase.R

@Composable
fun HomeCategoryView() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_chinese_plum_flower
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_flat_flower
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_giftbox
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_wedding_arch
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeCategoryView() {
    HomeCategoryView()
}