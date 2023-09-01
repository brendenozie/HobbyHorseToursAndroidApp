package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.EpisodesResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 19.03.2023
 */

interface EpisodesRemoteDataSource {
    suspend fun getAllEpisodes(): Flow<DataState<EpisodesResponse>>
    suspend fun getEpisode(episodeId: Int): Flow<DataState<EpisodesResponse>>
}