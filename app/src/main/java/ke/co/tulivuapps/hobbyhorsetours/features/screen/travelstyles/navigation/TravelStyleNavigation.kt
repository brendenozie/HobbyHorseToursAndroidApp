@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.TravelStylesScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val travelStyleNavigationRoute = "travel_style_route"

fun NavController.navigateTravelStyle(
    navOptions: NavOptions? = null
) {
    this.navigate(travelStyleNavigationRoute, navOptions)
}

fun NavGraphBuilder.travelStylesScreen(navController: NavHostController) {
    composable(travelStyleNavigationRoute) {
        TravelStylesScreen(viewModel = hiltViewModel(), navigateToDetail = {})
    }
}
