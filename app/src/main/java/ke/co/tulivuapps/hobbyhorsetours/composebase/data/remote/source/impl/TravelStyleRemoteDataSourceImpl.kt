package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelStyle.TravelStyleInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelStyle.TravelStyleResponse
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
    private val dao: FavoriteDao
) :
    BaseRemoteDataSource(), TravelStyleRemoteDataSource {

    override suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getAllTravelStyles(
        page: Int,
        options: Map<String, String>
    ): Response<TravelStyleResponse> = travelStyleService.getAllTravelStyles(page, options)

    override suspend fun getFilterTravelStyles(
        page: Int, options: Map<String, String>
    ): Response<TravelStyleResponse> = travelStyleService.getFilterTravelStyle(page, options)

    override suspend fun getTravelStyle(travelStyleId: Int): Flow<DataState<TravelStyleInfoResponse>> =
        getResult { travelStyleService.getTravelStyle(travelStyleId = travelStyleId) }

    override suspend fun getTravelStyle(url: String): Flow<DataState<TravelStyleInfoResponse>> =
        getResult {
            travelStyleService.getTravelStyle(url)
        }


    override suspend fun getFavoriteList(): List<FavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: FavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<FavoriteEntity>) = dao.insert(entityList)

}
