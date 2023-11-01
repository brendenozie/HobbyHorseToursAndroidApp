package ke.co.tulivuapps.hobbyhorsetours.domain.repository

import ke.co.tulivuapps.hobbyhorsetours.data.model.city.CityFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.city.CityResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface CityRepository {
    suspend fun getAllCities(page: Int, options: Map<String, String>): Response<CityResponse>
    fun getCity(characterId: Int): Flow<DataState<CityInfoResponse>>
    fun getCity(url: String): Flow<DataState<CityInfoResponse>>
    suspend fun getFilterCity(page: Int, options: Map<String, String>): Response<CityResponse>
    suspend fun getCityFavoriteList(): List<CityFavoriteEntity>
    suspend fun getFavoriteCity(favoriteId: Int): CityFavoriteEntity? = null
    suspend fun deleteFavoriteCityById(favoriteId: Int)
    suspend fun deleteFavoriteCityList()
    suspend fun saveFavoriteCity(entity: CityFavoriteEntity)
    suspend fun saveFavoriteCityList(entityList: List<CityFavoriteEntity>)
}