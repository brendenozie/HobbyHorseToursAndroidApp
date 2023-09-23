@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.BookNowScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val bookNowNavigationRoute = "book_now_route"

fun NavController.navigateToBookNow(
    bookingDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(bookNowNavigationRoute.plus("?bookingDetail=${bookingDetail}"), navOptions)
}

fun NavGraphBuilder.bookNowScreen(bookingDetail: (BookingDto) -> Unit) {
    composable(
        bookNowNavigationRoute.plus("?bookingDetail=${bookingDetail}"),
        content = {
            BookNowScreen(
                viewModel = hiltViewModel(),
                navigateBookingDetail = {bookingDetail
                    //navigateCharacterDetail.invoke(it)
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