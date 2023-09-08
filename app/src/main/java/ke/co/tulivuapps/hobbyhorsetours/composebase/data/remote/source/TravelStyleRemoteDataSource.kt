package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.TravelStyleFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelstyle.TravelStyleInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelstyle.TravelStyleResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface TravelStyleRemoteDataSource {
    suspend fun getAllTravelStyle(page: Int, options: Map<String, String>): Response<TravelStyleResponse>
    suspend fun getFilterTravelStyle(page: Int, options: Map<String, String>): Response<TravelStyleResponse>
    suspend fun getTravelStyle(travelStyleId: Int): Flow<DataState<TravelStyleInfoResponse>>
    suspend fun getTravelStyle(url: String): Flow<DataState<TravelStyleInfoResponse>>
    suspend fun getFavoriteList(): List<TravelStyleFavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): TravelStyleFavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int)
    suspend fun deleteFavoriteList()
    suspend fun saveFavorite(entity: TravelStyleFavoriteEntity)
    suspend fun saveFavoriteList(entityList: List<TravelStyleFavoriteEntity>)

}