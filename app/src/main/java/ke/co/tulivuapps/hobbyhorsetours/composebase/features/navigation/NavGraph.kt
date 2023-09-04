package ke.co.tulivuapps.hobbyhorsetours.composebase.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursBottomAppBar
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursFloatingActionBar
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.characters.navigation.homesNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.charactersdetail.navigation.charactersDetailScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.charactersdetail.navigation.navigateCharactersDetail
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.episodes.navigation.episodesScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.favorites.navigation.favoritesScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home.navigation.homesScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.hotelsdetail.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.search.navigation.searchScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.settings.navigation.settingsScreen
import ke.co.tulivuapps.hobbyhorsetours.composebase.utils.Utility.toJson

/**
 * Created by brendenozie on 9.03.2023
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination

    HobbyHorseToursScaffold(
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    HobbyHorseToursBottomAppBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    HobbyHorseToursFloatingActionBar(
                        navController = navController,
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = homesNavigationRoute,
            Modifier.padding(innerPadding)
        ) {
            homesScreen(navController)
            charactersDetailScreen { navController.navigateUp() }
            episodesScreen()
            searchScreen { navController.navigateCharactersDetail(it.toJson()) }
            settingsScreen()
            favoritesScreen { navController.navigateCharactersDetail(it.toJson()) }
        }
    }
}


