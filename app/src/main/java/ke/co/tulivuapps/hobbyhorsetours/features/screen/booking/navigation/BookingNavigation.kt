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
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booking.BookingsScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val bookingsNavigationRoute = "bookings_route"

fun NavController.navigateToBookings(
    navOptions: NavOptions? = null
) {
    this.navigate(bookingsNavigationRoute, navOptions)
}

fun NavGraphBuilder.bookingsScreen(navigateCharacterDetail: (BookingDto) -> Unit) {
    composable(
      bookingsNavigationRoute,
        content = {
            BookingsScreen(
                viewModel = hiltViewModel(),
                navigateBookingDetail = {
                    navigateCharacterDetail.invoke(it)
                }
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