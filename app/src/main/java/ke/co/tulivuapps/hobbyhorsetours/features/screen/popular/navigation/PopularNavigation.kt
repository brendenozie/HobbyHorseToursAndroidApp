@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.popular.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hobbyhorsetours.features.screen.popular.PopularScreen
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 23.01.2023
 */

const val popularNavigationRoute = "popular_route"

fun NavController.navigateToPopular(
    navOptions: NavOptions? = null
) {
    this.navigate(popularNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.episodesScreen(navController: NavHostController) {
    composable(popularNavigationRoute,
        content={
            PopularScreen(navigateToDetail = {navController.navigateHotelsDetail(it.toJson())}, viewModel = hiltViewModel() )
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
        })
}