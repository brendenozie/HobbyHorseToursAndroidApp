package ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.CityDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.CityFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.city.CityResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.CityRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */

class CityRepositoryImpl @Inject constructor(
    private val cityRemoteDataSource: CityRemoteDataSource,
    private val dao: CityDao
) : CityRepository {

    override suspend fun getAllCities(
        page: Int,
        options: Map<String, String>
    ): Response<CityResponse> =
        cityRemoteDataSource.getAllCities(page = page, options = options)

    override fun getCity(cityId: Int): Flow<DataState<CityInfoResponse>> = flow {
        emitAll(cityRemoteDataSource.getCity(cityId = cityId))
    }

    override fun getCity(url: String): Flow<DataState<CityInfoResponse>> = flow {
        emitAll(cityRemoteDataSource.getCity(url = url))
    }

    override suspend fun getFilterCity(
        page: Int,
        options: Map<String, String>
    ): Response<CityResponse> = cityRemoteDataSource.getFilterCities(page, options)

    override suspend fun getFavoriteCity(favoriteId: Int): CityFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getCityFavoriteList(): List<CityFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteCityById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteCityList() = dao.deleteFavoriteList()

    override suspend fun saveFavoriteCity(entity: CityFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteCityList(entityList: List<CityFavoriteEntity>) = dao.insert(entityList)
}