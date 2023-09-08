@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.destinationsdetail.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.destinationsdetail.DetailScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val destinationsDetailNavigationRoute = "destinations_detail_route"

fun NavController.navigateDestinationsDetail(
    destinationDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(destinationsDetailNavigationRoute.plus("?destinationDetail=${destinationDetail}"), navOptions)
}

fun NavGraphBuilder.destinationsDetailScreen(navigateToBack: () -> Unit) {
    composable(
        destinationsDetailNavigationRoute.plus("?destinationDetail={destinationDetail}"),
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