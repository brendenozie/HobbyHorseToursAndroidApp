@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.characters.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Created by brendenozie on 23.01.2023
 */

const val homesNavigationRoute = "homes_route"

fun NavController.navigateHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homesNavigationRoute, navOptions)
}

//fun NavGraphBuilder.charactersScreen(navigateToDestination: (DestinationDto?)->Unit) {
//    composable(homeNavigationRoute) {
//        HomeScreen(
//                    destinationsViewModel =hiltViewModel(),
//                    travelstyleviewModel =hiltViewModel(),
//                    cityviewModel =hiltViewModel(),
//                    hotelsviewModel =hiltViewModel(),
//                    navigateToDestination = {navController.navigateDestinationsDetail(it.toJson())}
//        ) {
//            navigateToDestination.invoke(it)
//        }
//    }
//}
