package com.guru.composecookbook.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.composebase.R
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.profile.BottomScrollingContent
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.profile.TopScrollingContent

const val initialImageFloat = 170f
const val name = "Gurupreet Singh"
const val email = "gurpreet.usit@gmail.com"
const val twitterUrl = "https://www.twitter.com/_gurupreet"
const val linkedInUrl = "https://www.linkedin.com/in/gurupreet-singh-491a7668/"
const val githubUrl = "https://github.com"
const val githubRepoUrl = "https://github.com/Gurupreet/ComposeCookBook"

//NOTE: This stuff should usually be in a parent activity/Navigator
// We can pass callback to profileScreen to get the click.
internal fun launchSocialActivity(context: Context, socialType: String) {
    val intent = when (socialType) {
        "github" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
        "repository" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubRepoUrl))
        "linkedin" -> Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
        else -> Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
    }
    context.startActivity(intent)
}


@Composable
fun ProfileScreen() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .semantics { testTag = "Profile Screen" }
        ) {
            val scrollState = rememberScrollState(0)
            TopAppBarView(scrollState.value.toFloat())
            TopBackground()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                TopScrollingContent(scrollState)
                BottomScrollingContent()
            }
        }
    }
}

@Composable
fun TopAppBarView(scroll: Float) {
    if (scroll > initialImageFloat + 5) {
        TopAppBar(
            title = {
                Text(text = name)
            },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.coffee),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        )
    }
}

@Composable
private fun TopBackground() {
    val gradient = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primary.copy(alpha = 0.7f))
    Spacer(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
//            .f(gradient)
//            .horizontalGradientBackground(gradient)
    )
}

@Preview
@Composable
fun ShowProfileScreen() {
    ProfileScreen()
}