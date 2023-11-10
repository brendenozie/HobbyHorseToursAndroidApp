@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.profile.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.login.navigation.navigateToLogin
import ke.co.tulivuapps.hobbyhorsetours.features.screen.profile.ProfileScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val profileNavigationRoute = "profile_route"

fun NavController.navigateToProfile(
    navOptions: NavOptions? = null
) {
    this.navigate(profileNavigationRoute, navOptions)
}

fun NavGraphBuilder.profileScreen(navController: NavHostController) {
    composable(profileNavigationRoute,
        content={
        ProfileScreen(profileViewModel = hiltViewModel(),login={navController.navigateToLogin() })
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