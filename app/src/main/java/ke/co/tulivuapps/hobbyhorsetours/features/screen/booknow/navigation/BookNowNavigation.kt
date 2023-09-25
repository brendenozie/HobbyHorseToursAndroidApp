@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow.BookNowScreen
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val bookNowNavigationRoute = "book_now_route"

fun NavController.navigateToBookNow(navOptions: NavOptions? = null) {
    this.navigate(bookNowNavigationRoute, navOptions)
}

fun NavGraphBuilder.bookNowScreen(navController: NavHostController) {
    composable(bookNowNavigationRoute) {
        BookNowScreen(
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