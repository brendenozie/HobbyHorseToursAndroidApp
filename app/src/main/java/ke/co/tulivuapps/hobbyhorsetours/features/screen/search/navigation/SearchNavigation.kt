@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.search.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotelsdetail.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.search.SearchScreen
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(
    navOptions: NavOptions? = null
) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(navController: NavHostController) {
    composable(searchNavigationRoute) {
        SearchScreen(
            hiltViewModel(),
            navigateToHotelDto = {
                navController.navigateHotelsDetail(it.toJson())
            }
            ,navigateToBack = {
                navController.navigateUp()
            }
            ,
            navigateToDestinationDto = {
                navController.navigateDestinationsDetail(it.toJson())
            }
        )
    }
}