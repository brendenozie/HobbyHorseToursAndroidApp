package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.co.tulivuapps.hobbyhorsetours.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopBar(onNavigationIconClick: () -> Unit, isSignedIn: Boolean, visibility: MutableTransitionState<Boolean> = MutableTransitionState(false), username: String?) {

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        DialogBox(showDialog = showDialog.value,
            dismissDialog = { showDialog.value = false })
    }

    AnimatedVisibility(visibility,
        enter = EnterTransition.None,
        exit = ExitTransition.None) {
        TopAppBar(
            elevation = 0.dp,
            modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
            title = {
                Column(modifier = Modifier
                    .width(200.dp)
                    .height(60.dp)
                    .clickable(onClick = { onNavigationIconClick.invoke() })
                ) {
                    Text(
                        text = if (isSignedIn) "Welcome," else "Hello, Your not Signed In,",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.animateEnterExit(
                            enter = slideIn(
                                tween(
                                    delayMillis = 300,
                                    easing = LinearOutSlowInEasing,
                                    durationMillis = 800
                                )
                            ) { IntOffset(0, 120) },
                        )
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = if (isSignedIn && username != null) username else "Click to sign in",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        style = TextStyle(color = if (isSystemInDarkTheme()) Color.White else Color.Black),
                        modifier = Modifier.animateEnterExit(
                            enter = slideIn(
                                tween(
                                    delayMillis = 700,
                                    easing = LinearOutSlowInEasing,
                                    durationMillis = 500
                                )
                            ) { IntOffset(0, 120) },
                        )
                    )
                }
            },
            navigationIcon = {
                val radius = 24.dp
                val shape = RoundedCornerShape(radius)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .defaultMinSize(minWidth = radius * 2, minHeight = radius * 2)
                            .background(
                                color = Color.White, shape = shape
                            )
                            .border(
                                1.dp,
                                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .clip(shape)
                            .animateEnterExit(
                                enter = slideIn(
                                    tween(
                                        delayMillis = 400,
                                        easing = LinearOutSlowInEasing,
                                        durationMillis = 500
                                    )
                                ) { IntOffset(0, 120) },
                    ),
                    ) {
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    showDialog.value = true
                                },
                            painter = painterResource(id = R.drawable.refer),
                            contentDescription = ""
                        )
                    }
            },
            actions = {
                    BadgedBox(modifier=Modifier.
                                animateEnterExit(
                                    enter = slideIn(
                                        tween(
                                            delayMillis = 400,
                                            easing = LinearOutSlowInEasing,
                                            durationMillis = 500
                                        )
                                    ) { IntOffset(0, 120) },
                                ),
                        badge = {
                            Badge(
                                modifier = Modifier
                                    .size(10.dp), backgroundColor = Color.Red
                            )
                        },
                    ) {
                        val radius = 22.dp
                        val shape = RoundedCornerShape(radius)
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .defaultMinSize(minWidth = radius * 2, minHeight = radius * 2)
                                .background(
                                    color = Color.White, shape = shape
                                )
                                .border(
                                    1.dp,
                                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                    shape = RoundedCornerShape(22.dp)
                                )
                                .clip(shape),
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        showDialog.value = true
                                    },
                                painter = painterResource(id = R.drawable.notification),
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                }
            },
            backgroundColor = Color.Transparent
        )
    }

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
    TopBar(onNavigationIconClick = {}, isSignedIn = false, username = "")
}