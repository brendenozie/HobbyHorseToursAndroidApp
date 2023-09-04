package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.episodes

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.episode.EpisodesResultResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewState

/**
 * Created by brendenozie on 19.03.2023
 */

@Stable
data class EpisodesViewState(
    val isLoading: Boolean = false,
    val data: List<EpisodesResultResponse>? = null
) : IViewState