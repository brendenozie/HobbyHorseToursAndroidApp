package ke.co.tulivuapps.hobbyhorsetours.data.repository

import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.PopularRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.PopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by brendenozie on 19.03.2023
 */

class PopularRepositoryImpl @Inject constructor(private val episodesRemoteDataSource: PopularRemoteDataSource) :
    PopularRepository {

    override fun getAllPopular(): Flow<DataState<PopularResponse>> = flow {
        emitAll(episodesRemoteDataSource.getAllPopular())
    }

    override fun getEpisode(episodeId: Int): Flow<DataState<ResultPopular>> = flow {
        emitAll(episodesRemoteDataSource.getPopular(episodeId))
    }
}