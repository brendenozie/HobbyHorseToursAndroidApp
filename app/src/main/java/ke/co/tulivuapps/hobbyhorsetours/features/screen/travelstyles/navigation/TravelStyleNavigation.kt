@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.TravelStylesScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val travelStyleNavigationRoute = "travel_style_route"

fun NavController.navigateToTravelStyle(
    navOptions: NavOptions? = null
) {
    this.navigate(travelStyleNavigationRoute, navOptions)
}

fun NavGraphBuilder.travelStylesScreen(navController: NavHostController) {
    composable(travelStyleNavigationRoute,content= {
        TravelStylesScreen(viewModel = hiltViewModel(),navigateToSearch = navController, navigateToBack = {navController.navigateUp()}, navigateToDetail = {})
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
        })
}
