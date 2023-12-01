@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.homee.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.navigation.navigateToCities
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.navigation.navigateToDestinations
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.homee.HomeeScreen
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.navigation.navigateToHotels
import ke.co.tulivuapps.hobbyhorsetours.features.screen.login.navigation.navigateToLogin
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.navigation.navigateToTravelStyle
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val homeeNavigationRoute = "homees_route"

fun NavController.navigateToHomee(
    navOptions: NavOptions? = null
) {
    this.navigate(homeeNavigationRoute, navOptions)
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeesScreen(navController: NavHostController) {
    composable(homeeNavigationRoute,
                content={
                    HomeeScreen(
                        homeViewModel = hiltViewModel(),
                        navigateToLogin = {navController.navigateToLogin()},
                        navigateToSearch = navController,
                        navigateToCities =  {navController.navigateToCities()},
                        navigateToTravelStyles= {navController.navigateToTravelStyle()},
                        navigateToDestinations= {navController.navigateToDestinations()},
                        navigateToHotels= {navController.navigateToHotels()},
                        navigateToDestination = {navController.navigateDestinationsDetail(it.toJson())}
                    ) {
                        navController.navigateHotelsDetail(it.toJson())
                    }
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                }
        )
}