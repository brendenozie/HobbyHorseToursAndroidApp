@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
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

fun NavGraphBuilder.profileScreen() {
    composable(profileNavigationRoute) {
        ProfileScreen( )
    }
}