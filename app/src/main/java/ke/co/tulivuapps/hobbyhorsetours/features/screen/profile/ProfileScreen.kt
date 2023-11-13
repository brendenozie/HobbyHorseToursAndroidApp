package ke.co.tulivuapps.hobbyhorsetours.features.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursText

const val initialImageFloat = 170f
//const val name = "Gurupreet Singh"
//const val email = "gurpreet.usit@gmail.com"
//const val twitterUrl = "https://www.twitter.com/_gurupreet"
//const val linkedInUrl = "https://www.linkedin.com/in/gurupreet-singh-491a7668/"
//const val githubUrl = "https://github.com"
//const val githubRepoUrl = "https://github.com/Gurupreet/ComposeCookBook"

//NOTE: This stuff should usually be in a parent activity/Navigator
// We can pass callback to profileScreen to get the click.
//internal fun launchSocialActivity(context: Context, socialType: String) {
//    val intent = when (socialType) {
//        "github" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
//        "repository" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubRepoUrl))
//        "linkedin" -> Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
//        else -> Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
//    }
//    context.startActivity(intent)
//}


@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel,login: () -> Unit?) {
    val scrollState = rememberScrollState(0);

    val isLoggedIn by profileViewModel.selectedLogin.collectAsState()
    val username = profileViewModel.selectedUsername.collectAsState().value
    val email = profileViewModel.selectedEmail.collectAsState().value
    val image = profileViewModel.selectedImg.collectAsState().value

    val scaffoldState = rememberScaffoldState()

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBarView(scrollState.value.toFloat(), username)
        },
        content = {paddingValues ->
            Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .semantics { testTag = "Profile Screen" }
        ) {

                if(username.isNotEmpty() || email.isNotEmpty() || image.isNotEmpty()) {
                    TopBackground()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                        item {
                            TopScrollingContent(scrollState, username, email, image)
                        }
                        item {
                            BottomScrollingContent()
                        }
                    }
                }
                else{
                    EmptyListAnimation(login)
                }
        }
        }
    )
}

@Composable
fun TopAppBarView(scroll: Float,username:String) {
    if (scroll > initialImageFloat + 5) {
        TopAppBar(
            title = {
                Text(text = username)
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
    ProfileScreen(profileViewModel = hiltViewModel(),{})
}


@Composable
private fun EmptyListAnimation(login: () -> Unit?) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_search))
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            LottieAnimation(
                composition,
                modifier = Modifier,
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
            HobbyHorseToursText(
                text = stringResource(R.string.not_logged_in_screen_empty_list_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .wrapContentHeight()
                    .clickable { login() },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}
