package ke.co.tulivuapps.hobbyhorsetours.domain.repository

import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 19.03.2023
 */
interface PopularRepository {
    fun getAllPopular(): Flow<DataState<PopularResponse>>
    fun getEpisode(episodeId: Int): Flow<DataState<ResultPopular>>
}