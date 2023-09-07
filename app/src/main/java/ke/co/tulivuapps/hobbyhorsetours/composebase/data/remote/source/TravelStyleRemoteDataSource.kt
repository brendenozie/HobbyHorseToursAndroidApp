package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
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
    suspend fun getTravelStyle(characterId: Int): Flow<DataState<TravelStyleInfoResponse>>
    suspend fun getTravelStyle(url: String): Flow<DataState<TravelStyleInfoResponse>>
    suspend fun getFavoriteList(): List<FavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int)
    suspend fun deleteFavoriteList()
    suspend fun saveFavorite(entity: FavoriteEntity)
    suspend fun saveFavoriteList(entityList: List<FavoriteEntity>)

}