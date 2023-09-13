package ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.data.model.episode.PopularResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.PopularService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.PopularRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by brendenozie on 19.03.2023
 */

class PopularRemoteDataSourceImpl @Inject constructor(private val episodesService: PopularService) :
    BaseRemoteDataSource(), PopularRemoteDataSource {

    override suspend fun getAllEpisodes(): Flow<DataState<PopularResponse>> = getResult {
        episodesService.getAllEpisodes()
    }

    override suspend fun getEpisode(episodeId: Int): Flow<DataState<PopularResponse>> = getResult {
        episodesService.getEpisode(episodeId = episodeId)
    }
}
