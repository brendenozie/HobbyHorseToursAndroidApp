package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.EpisodesResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.EpisodesService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.EpisodesRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by brendenozie on 19.03.2023
 */

class EpisodesRemoteDataSourceImpl @Inject constructor(private val episodesService: EpisodesService) :
    BaseRemoteDataSource(), EpisodesRemoteDataSource {

    override suspend fun getAllEpisodes(): Flow<DataState<EpisodesResponse>> = getResult {
        episodesService.getAllEpisodes()
    }

    override suspend fun getEpisode(episodeId: Int): Flow<DataState<EpisodesResponse>> = getResult {
        episodesService.getEpisode(episodeId = episodeId)
    }
}
