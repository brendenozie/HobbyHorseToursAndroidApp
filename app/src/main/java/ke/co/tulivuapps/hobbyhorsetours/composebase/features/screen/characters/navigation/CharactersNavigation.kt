@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.characters.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home.HomeScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home.navigation.homeNavigationRoute

/**
 * Created by brendenozie on 23.01.2023
 */

const val homesNavigationRoute = "homes_route"

fun NavController.navigateHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homesNavigationRoute, navOptions)
}

fun NavGraphBuilder.charactersScreen(navigateToDestination: (DestinationDto?)->Unit) {
    composable(homeNavigationRoute) {
        HomeScreen(
            hiltViewModel()
        ) {
            navigateToDestination.invoke(it)
        }
    }
}
