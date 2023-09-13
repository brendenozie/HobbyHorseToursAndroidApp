package ke.co.tulivuapps.hobbyhorsetours.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.data.model.episode.PopularResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 19.03.2023
 */

interface PopularRemoteDataSource {
    suspend fun getAllEpisodes(): Flow<DataState<PopularResponse>>
    suspend fun getEpisode(episodeId: Int): Flow<DataState<PopularResponse>>
}