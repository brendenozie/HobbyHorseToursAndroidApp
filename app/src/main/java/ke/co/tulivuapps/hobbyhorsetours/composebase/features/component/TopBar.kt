package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.co.tulivuapps.hobbyhorsetours.composebase.R


@Composable
fun TopBar(onNavigationIconClick: () -> Unit) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        DialogBox(showDialog = showDialog.value,
            dismissDialog = { showDialog.value = false })
    }
    TopAppBar(elevation = 0.dp,modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp), title = {
        Column {
            Text(
                text = "Hi, Good Afternoon",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = "Timo",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                style = TextStyle(color = if (isSystemInDarkTheme()) Color.Black else Color.White)
            )
        }
    }, navigationIcon = {
        Image(painter = painterResource(id = R.drawable.coffee),
            contentDescription = "profile image",
            modifier = Modifier
                .size(58.dp)
                .clip(
                    CircleShape
                )
                .border(1.dp,color = if (isSystemInDarkTheme()) Color.Black else Color.White)
                .clickable {
                    onNavigationIconClick()
                })
    }, actions = {
        BadgedBox(
            badge = {
                Badge(
                    modifier = Modifier
                        .size(10.dp), backgroundColor = Color.Red
                )
            },
        ) {
            val radius = 20.dp
            val shape = RoundedCornerShape(radius)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .defaultMinSize(minWidth = radius * 2, minHeight = radius * 2)
                    .background(
                        color = Color.White, shape = shape
                    )
                    .border(1.dp,color = if (isSystemInDarkTheme()) Color.Black else Color.White,shape = RoundedCornerShape(20.dp))
                    .clip(shape),
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            showDialog.value = true
                        },
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }

    }, backgroundColor = Color.Transparent)
}


@Composable
fun DialogBox(
    showDialog: Boolean,
    dismissDialog: () -> Unit
) {

    AlertDialog(onDismissRequest = { dismissDialog() }, title = {
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Notification permission needed",
                maxLines = 2,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp, textAlign = TextAlign.Center
            )
            Text(
                text = "Go to Settings to allow Notification access",
                maxLines = 2,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp, textAlign = TextAlign.Center
            )
        }
    }, buttons = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp), horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { dismissDialog() }) {
                Text(text = "Dismiss")
            }
            Spacer(modifier = Modifier.width(25.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Dismiss")
            }
        }
    })
}


@Preview(showBackground = true)
@Composable
fun PreviewHeaderItem() {
    TopBar(onNavigationIconClick = {})
}