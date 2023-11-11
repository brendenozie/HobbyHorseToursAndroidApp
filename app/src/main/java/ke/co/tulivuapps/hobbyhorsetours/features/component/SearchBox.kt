package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.co.tulivuapps.hobbyhorsetours.R


@Composable
fun SearchBox(detailClick: () -> Unit) {
//    val visibility = remember { mutableStateOf(false) }
//
//    LaunchedEffect(key1 = true) {
//        delay(1000L)
//        visibility.value = true
//    }

    Row(modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(),horizontalArrangement = Arrangement.Center ) {
//        AnimatedVisibility(visibility.value,
//            enter = expandIn(
//                tween(
//                    delayMillis = 900,
//                    easing = LinearOutSlowInEasing,
//                    durationMillis = 500
//                )
//            ) { IntSize(0,0) },
//        ) {
            TextField(modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .clickable(onClick = detailClick),
                    enabled = false,
                    readOnly = true,
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(30.dp),
                    placeholder = {
                        Text(
                            text = "Search...",
                            color = Color.LightGray,
                            fontSize = 13.sp,
                        )
                    },
                    singleLine = true,
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape)
                                .background(Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_filter),
                                contentDescription = "Filter Icon", tint = Color.White
                            )

                        }
                    },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search Icon",
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Gray,
                        disabledTextColor = Color.Transparent,
    //                    backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
//        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewSearchBox() {
    SearchBox({})
}