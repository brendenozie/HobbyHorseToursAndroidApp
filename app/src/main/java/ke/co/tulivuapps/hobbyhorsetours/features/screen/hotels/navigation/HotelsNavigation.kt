@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.HotelsScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val hotelsNavigationRoute = "hotels_route"

fun NavController.navigateHotels(
    navOptions: NavOptions? = null
) {
    this.navigate(hotelsNavigationRoute, navOptions)
}

fun NavGraphBuilder.hotelsScreen(navController: NavHostController) {
    composable(hotelsNavigationRoute) {
        HotelsScreen(viewModel = hiltViewModel(), navigateToDetail = {})
    }
}
