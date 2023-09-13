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
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotelsdetail.DetailScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val hotelsDetailNavigationRoute = "hotels_detail_route"

fun NavController.navigateHotelsDetail(
    characterDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(hotelsDetailNavigationRoute.plus("?hotelDetail=${characterDetail}"), navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.hotelsDetailScreen(navigateToBack: () -> Unit) {
    composable(
        hotelsDetailNavigationRoute.plus("?hotelDetail={hotelDetail}"),
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