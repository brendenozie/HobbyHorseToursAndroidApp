@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.bookingdetail.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.bookingdetail.DetailScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val bookingDetailNavigationRoute = "booking_detail_route"

fun NavController.navigateBookingDetail(
    bookingDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(bookingDetailNavigationRoute.plus("?bookingDetail=${bookingDetail}"), navOptions)
}

fun NavGraphBuilder.bookingDetailScreen(navigateToBack: () -> Unit) {
    composable(
        bookingDetailNavigationRoute.plus("?bookingDetail={bookingDetail}"),
        content = {
            DetailScreen(
                viewModel = hiltViewModel(),
                navigateToBack = navigateToBack
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