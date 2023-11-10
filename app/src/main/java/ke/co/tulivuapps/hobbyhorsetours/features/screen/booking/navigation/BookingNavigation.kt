@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.booking.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booking.BookingsScreen
import ke.co.tulivuapps.hobbyhorsetours.features.screen.bookingdetail.navigation.navigateBookingDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.login.navigation.navigateToLogin
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val bookingsNavigationRoute = "bookings_route"

fun NavController.navigateToBookings(
    navOptions: NavOptions? = null
) {
    this.navigate(bookingsNavigationRoute, navOptions)
}

fun NavGraphBuilder.bookingsScreen(navController: NavController) {
    composable(
      bookingsNavigationRoute,
        content = {
            BookingsScreen(
                viewModel = hiltViewModel(),
                navigateBookingDetail = {navController.navigateBookingDetail(it.toJson())},
                navigateToLogin = {navController.navigateToLogin()}
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