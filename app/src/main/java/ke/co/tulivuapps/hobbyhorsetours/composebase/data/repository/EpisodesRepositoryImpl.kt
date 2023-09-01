package ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.EpisodesResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.EpisodesRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by brendenozie on 19.03.2023
 */

class EpisodesRepositoryImpl @Inject constructor(private val episodesRemoteDataSource: EpisodesRemoteDataSource) :
    EpisodesRepository {

    override fun getAllEpisodes(): Flow<DataState<EpisodesResponse>> = flow {
        emitAll(episodesRemoteDataSource.getAllEpisodes())
    }

    override fun getEpisode(episodeId: Int): Flow<DataState<EpisodesResponse>> = flow {
        emitAll(episodesRemoteDataSource.getEpisode(episodeId))
    }
}