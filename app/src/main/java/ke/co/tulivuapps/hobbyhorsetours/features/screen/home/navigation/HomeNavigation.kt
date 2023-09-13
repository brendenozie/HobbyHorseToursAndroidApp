@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.characters.navigation.homesNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.navigation.navigateCities
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.navigation.navigateDestinations
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.home.HomeScreen
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.navigation.navigateHotels
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotelsdetail.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.search.navigation.navigateToSearch
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.navigation.navigateTravelStyle
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val homeNavigationRoute = "homes_route"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homeNavigationRoute, navOptions)
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homesScreen(navController: NavHostController) {
    composable(homesNavigationRoute) {
        HomeScreen(
            destinationsViewModel =hiltViewModel(),
            travelstyleviewModel =hiltViewModel(),
            cityviewModel =hiltViewModel(),
            hotelsviewModel =hiltViewModel(),
            navigateToSearch = {navController.navigateToSearch()},
            navigateToCities =  {navController.navigateCities()},
            navigateToTravelStyles= {navController.navigateTravelStyle()},
            navigateToDestinations= {navController.navigateDestinations()},
            navigateToHotels= {navController.navigateHotels()},
            navigateToDestination = {navController.navigateDestinationsDetail(it.toJson())}
        ) {
            navController.navigateHotelsDetail(it.toJson())
        }
    }
}