package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.city.CityResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface CityRemoteDataSource {
    suspend fun getAllCities(page: Int, options: Map<String, String>): Response<CityResponse>
    suspend fun getFilterCities(page: Int, options: Map<String, String>): Response<CityResponse>
    suspend fun getCity(characterId: Int): Flow<DataState<CityInfoResponse>>
    suspend fun getCity(url: String): Flow<DataState<CityInfoResponse>>
    suspend fun getFavoriteList(): List<FavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int)
    suspend fun deleteFavoriteList()
    suspend fun saveFavorite(entity: FavoriteEntity)
    suspend fun saveFavoriteList(entityList: List<FavoriteEntity>)

}