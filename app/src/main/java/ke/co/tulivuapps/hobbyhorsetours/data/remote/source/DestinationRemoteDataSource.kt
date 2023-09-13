package ke.co.tulivuapps.hobbyhorsetours.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.data.model.DestinationFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.data.model.destination.DestinationInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.destination.DestinationResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface DestinationRemoteDataSource {
    suspend fun getAllDestinations(page: Int, options: Map<String, String>): Response<DestinationResponse>
    suspend fun getFilterDestinations(page: Int, options: Map<String, String>): Response<DestinationResponse>
    suspend fun getDestination(characterId: Int): Flow<DataState<DestinationInfoResponse>>
    suspend fun getDestination(url: String): Flow<DataState<DestinationInfoResponse>>
    suspend fun getFavoriteList(): List<DestinationFavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): DestinationFavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int)
    suspend fun deleteFavoriteList()
    suspend fun saveFavorite(entity: DestinationFavoriteEntity)
    suspend fun saveFavoriteList(entityList: List<DestinationFavoriteEntity>)

}