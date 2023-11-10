package ke.co.tulivuapps.hobbyhorsetours.domain.repository

import ke.co.tulivuapps.hobbyhorsetours.data.model.travelstyle.TravelStyleFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.data.model.travelstyle.TravelStyleInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.travelstyle.TravelStyleResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface TravelStyleRepository {
    suspend fun getAllTravelStyles(page: Int, options: Map<String, String>): Response<TravelStyleResponse>
    fun getTravelStyle(travelStyleId: Int): Flow<DataState<TravelStyleInfoResponse>>
    fun getTravelStyle(url: String): Flow<DataState<TravelStyleInfoResponse>>
    suspend fun getFilterTravelStyle(page: Int, options: Map<String, String>): Response<TravelStyleResponse>
    suspend fun getTravelStyleFavoriteList(): List<TravelStyleFavoriteEntity>
    suspend fun getFavoriteTravelStyle(favoriteId: Int): TravelStyleFavoriteEntity? = null
    suspend fun deleteFavoriteTravelStyleById(favoriteId: Int)
    suspend fun deleteFavoriteTravelStyleList()
    suspend fun saveFavoriteTravelStyle(entity: TravelStyleFavoriteEntity)
    suspend fun saveFavoriteTravelStyleList(entityList: List<TravelStyleFavoriteEntity>)
}