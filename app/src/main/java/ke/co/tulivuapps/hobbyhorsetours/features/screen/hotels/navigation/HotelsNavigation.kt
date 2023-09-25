@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.HotelsScreen
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotelsdetail.navigation.hotelsDetailNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val hotelsNavigationRoute = "hotels_route"

fun NavController.navigateHotelsDetail(
    hotelDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(hotelsDetailNavigationRoute.plus("?hotelDetail=${hotelDetail}"), navOptions)
}

fun NavController.navigateToHotels(
    navOptions: NavOptions? = null
) {
    this.navigate(hotelsNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.hotelsScreen(navController: NavHostController) {
    composable(hotelsNavigationRoute) {
        HotelsScreen(viewModel = hiltViewModel(), navigateToDetail = {navController.navigateHotelsDetail(it.toJson())})
    }
}
