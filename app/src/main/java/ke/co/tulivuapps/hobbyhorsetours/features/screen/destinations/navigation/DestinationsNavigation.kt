@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.DestinationsScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val destinationsNavigationRoute = "destinations_route"

fun NavController.navigateDestinations(
    navOptions: NavOptions? = null
) {
    this.navigate(destinationsNavigationRoute, navOptions)
}

fun NavGraphBuilder.destinationScreen(navController: NavHostController) {
    composable(destinationsNavigationRoute) {
        DestinationsScreen(viewModel = hiltViewModel(), navigateToDetail = {}  )
    }
}
