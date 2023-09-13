package ke.co.tulivuapps.hobbyhorsetours.features.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.features.screen.booking.navigation.bookingsNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.features.screen.popular.navigation.episodesNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.features.screen.favorites.navigation.favoritesNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.features.screen.home.navigation.homeNavigationRoute
import ke.co.tulivuapps.hobbyhorsetours.features.screen.profile.navigation.profileNavigationRoute

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
    POPULAR(
        episodesNavigationRoute,
        R.drawable.ic_baseline_movie_creation_24,
        R.string.popular_screen_title
    ),
    FAVORITES(
        favoritesNavigationRoute,
        R.drawable.ic_baseline_favorite_24,
        R.string.favorite_screen_title,
    ),
    BOOKING(
        bookingsNavigationRoute,
        R.drawable.ic_baseline_settings,
        R.string.booking_screen_title,
    ),
    PROFILE(
        profileNavigationRoute,
        R.drawable.ic_outline_people,
        R.string.profile_screen_title
    )
}