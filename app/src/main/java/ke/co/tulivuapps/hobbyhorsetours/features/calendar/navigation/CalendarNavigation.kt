@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.calendar.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.calendar.CalendarScreen
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.BookNowViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.navigation.bookNowNavigationRoute

/**
 * Created by brendenozie on 23.01.2023
 */

const val calendarNavigationRoute = "calendar_route"

fun NavController.navigateToCalendar(navOptions: NavOptions? = null) {
    this.navigate(calendarNavigationRoute, navOptions)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.calendarScreen(navController: NavHostController) {
    composable(
        calendarNavigationRoute,
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
    ){
        val parentEntry = remember(it) {
            navController.getBackStackEntry(bookNowNavigationRoute.plus("?bookingNow={bookingNow}"))
        }
        val parentViewModel = hiltViewModel<BookNowViewModel>(
            parentEntry
        )

        CalendarScreen(bookNowViewModel = parentViewModel, onBackPressed = { navController.navigateUp() })

    }
}