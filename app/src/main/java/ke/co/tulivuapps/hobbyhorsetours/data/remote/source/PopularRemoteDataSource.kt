package ke.co.tulivuapps.hobbyhorsetours.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 19.03.2023
 */

interface PopularRemoteDataSource {
    suspend fun getAllPopular(): Flow<DataState<PopularResponse>>
    suspend fun getPopular(episodeId: Int): Flow<DataState<ResultPopular>>
}