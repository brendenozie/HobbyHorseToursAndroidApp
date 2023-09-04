package ke.co.tulivuapps.hobbyhorsetours.composebase.features.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ke.co.tulivuapps.hobbyhorsetours.composebase.R
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home.navigation.homeNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.episodes.navigation.episodesNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.favorites.navigation.favoritesNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.search.navigation.searchNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.settings.navigation.settingsNavigationRoute

/**
 * Created by brendenozie on 9.03.2023
 */

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
    HOME(
        homeNavigationRoute,
        R.drawable.ic_outline_people,
        R.string.home_screen_title,
    ),
    EPISODES(
        episodesNavigationRoute,
        R.drawable.ic_baseline_movie_creation_24,
        R.string.episodes_screen_title
    ),
    FAVORITES(
        favoritesNavigationRoute,
        R.drawable.ic_baseline_favorite_24,
        R.string.favorite_screen_title,
    ),
    SEARCH(
        searchNavigationRoute,
        R.drawable.ic_baseline_search_24,
        R.string.search_screen_title,
    ),
    SETTINGS(
        settingsNavigationRoute,
        R.drawable.ic_baseline_settings,
        R.string.settings_screen_title
    )
}