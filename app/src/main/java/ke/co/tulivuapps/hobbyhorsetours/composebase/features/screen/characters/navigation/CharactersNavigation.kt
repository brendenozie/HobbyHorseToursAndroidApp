@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.characters.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.characters.CharactersScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val charactersNavigationRoute = "characters_route"

fun NavController.navigateCharacter(
    navOptions: NavOptions? = null
) {
    this.navigate(charactersNavigationRoute, navOptions)
}

fun NavGraphBuilder.charactersScreen(navigateToDetail: (CharacterDto?) -> Unit) {
    composable(charactersNavigationRoute) {
        CharactersScreen(
            hiltViewModel(),
            navigateToDetail = {
                navigateToDetail.invoke(it)
            }
        )
    }
}