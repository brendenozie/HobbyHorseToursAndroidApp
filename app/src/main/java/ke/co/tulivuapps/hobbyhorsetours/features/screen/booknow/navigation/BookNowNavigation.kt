@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.calendar.navigation.navigateToCalendar
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.BookNowScreen
import ke.co.tulivuapps.hobbyhorsetours.features.screen.login.navigation.navigateToLogin

/**
 * Created by brendenozie on 23.01.2023
 */

const val bookNowNavigationRoute = "book_now_route"

fun NavController.navigateToBookNow(bookingNow: String,navOptions: NavOptions? = null) {
    this.navigate(bookNowNavigationRoute.plus("?bookingNow=${bookingNow}"), navOptions)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.bookNowScreen(navController: NavHostController) {

    composable(
        bookNowNavigationRoute.plus("?bookingNow={bookingNow}"),
        content = {
            BookNowScreen(
                viewModel = hiltViewModel(),
                navigateToBack = {navController.navigateUp()},
                navigateToCalender = {navController.navigateToCalendar()},
                navigateToLogin = { navController.navigateToLogin() }
            )
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
    )
}