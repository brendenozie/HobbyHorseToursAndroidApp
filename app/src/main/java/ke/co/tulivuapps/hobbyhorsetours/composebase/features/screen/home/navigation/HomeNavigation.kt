@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.characters.navigation.homesNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home.HomeScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.hotelsdetail.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hobbyhorsetours.composebase.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val homeNavigationRoute = "homes_route"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homeNavigationRoute, navOptions)
}


fun NavGraphBuilder.homesScreen(navController: NavHostController) {
    composable(homesNavigationRoute) {
        HomeScreen(
            hiltViewModel(),
            navigateToDestination = {
                navController.navigateDestinationsDetail(it.toJson())
            },
            navigateToHotel = {
                navController.navigateHotelsDetail(it.toJson())
            }
        )
    }
}