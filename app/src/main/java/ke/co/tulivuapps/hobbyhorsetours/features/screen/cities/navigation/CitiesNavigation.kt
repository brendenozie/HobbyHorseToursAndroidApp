@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.CityScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val citiesNavigationRoute = "cities_route"

fun NavController.navigateCities(
    navOptions: NavOptions? = null
) {
    this.navigate(citiesNavigationRoute, navOptions)
}

fun NavGraphBuilder.citiesScreen(navController: NavHostController) {
    composable(citiesNavigationRoute) {
        CityScreen(viewModel = hiltViewModel(), navigateToDetail = {})
    }
}
