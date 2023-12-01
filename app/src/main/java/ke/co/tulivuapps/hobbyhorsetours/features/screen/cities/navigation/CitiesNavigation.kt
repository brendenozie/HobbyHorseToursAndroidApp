@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.CitiesScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val citiesNavigationRoute = "cities_route"

fun NavController.navigateToCities(
    navOptions: NavOptions? = null
) {
    this.navigate(citiesNavigationRoute, navOptions)
}

fun NavGraphBuilder.citiesScreen(navController: NavHostController) {
    composable(
        citiesNavigationRoute,
        content = {
                CitiesScreen(viewModel = hiltViewModel(), navigateToSearch = navController,navigateToBack= {navController.navigateUp()},navigateToDetail = {})
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
