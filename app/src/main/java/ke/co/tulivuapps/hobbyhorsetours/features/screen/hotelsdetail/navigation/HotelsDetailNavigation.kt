@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.hotelsdetail.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.navigation.navigateToBookNow
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotelsdetail.DetailScreen
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val hotelsDetailNavigationRoute = "hotels_detail_route"

fun NavController.navigateHotelsDetail(
    hotelDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(hotelsDetailNavigationRoute.plus("?hotelDetail=${hotelDetail}"), navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.hotelsDetailScreen(navController: NavController) {
    composable(
        hotelsDetailNavigationRoute.plus("?hotelDetail={hotelDetail}"),
        content = {
            DetailScreen(
                viewModel = hiltViewModel(),
                navigateToBack = {navController.navigateUp()},
                navigateToBookNow = { navController.navigateToBookNow(it.toJson()) }
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