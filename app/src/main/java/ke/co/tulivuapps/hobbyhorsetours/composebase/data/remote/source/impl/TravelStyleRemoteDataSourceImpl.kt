package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.TravelStyleDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.TravelStyleFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelstyle.TravelStyleInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelstyle.TravelStyleResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.TravelStyleService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.TravelStyleRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */
class TravelStyleRemoteDataSourceImpl @Inject constructor(
    private val travelStyleService: TravelStyleService,
    private val dao: TravelStyleDao
) :
    BaseRemoteDataSource(), TravelStyleRemoteDataSource {

    override suspend fun getFavorite(favoriteId: Int): TravelStyleFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getAllTravelStyle(
        page: Int,
        options: Map<String, String>
    ): Response<TravelStyleResponse> = travelStyleService.getAllTravelStyles(page, options)

    override suspend fun getFilterTravelStyle(
        page: Int, options: Map<String, String>
    ): Response<TravelStyleResponse> = travelStyleService.getFilterTravelStyle(page, options)

    override suspend fun getTravelStyle(travelStyleId: Int): Flow<DataState<TravelStyleInfoResponse>> =
        getResult { travelStyleService.getTravelStyle(travelStyleId = travelStyleId) }

    override suspend fun getTravelStyle(url: String): Flow<DataState<TravelStyleInfoResponse>> =
        getResult {
            travelStyleService.getTravelStyle(url)
        }


    override suspend fun getFavoriteList(): List<TravelStyleFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: TravelStyleFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<TravelStyleFavoriteEntity>) = dao.insert(entityList)

}
