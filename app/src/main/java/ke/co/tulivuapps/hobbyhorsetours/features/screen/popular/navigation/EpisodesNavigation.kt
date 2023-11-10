@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.popular.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.popular.PopularScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val episodesNavigationRoute = "episodes_route"

fun NavController.navigateToEpisodes(
    navOptions: NavOptions? = null
) {
    this.navigate(episodesNavigationRoute, navOptions)
}

fun NavGraphBuilder.episodesScreen() {
    composable(episodesNavigationRoute,
        content={PopularScreen(
                        hiltViewModel()
                    )
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
        })
}