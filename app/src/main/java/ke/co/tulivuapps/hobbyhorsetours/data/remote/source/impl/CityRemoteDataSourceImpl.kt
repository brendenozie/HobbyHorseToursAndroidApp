package ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.CityDao
import ke.co.tulivuapps.hobbyhorsetours.data.model.CityFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.city.CityResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.CityService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.CityRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */
class CityRemoteDataSourceImpl @Inject constructor(
    private val cityService: CityService,
    private val dao: CityDao
) :
    BaseRemoteDataSource(), CityRemoteDataSource {

    override suspend fun getFavorite(favoriteId: Int): CityFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getAllCities(
        page: Int,
        options: Map<String, String>
    ): Response<CityResponse> = cityService.getAllCitys(page, options)

    override suspend fun getFilterCities(
        page: Int, options: Map<String, String>
    ): Response<CityResponse> = cityService.getFilterCity(page, options)

    override suspend fun getCity(cityId: Int): Flow<DataState<CityInfoResponse>> =
        getResult {
            cityService.getCity(cityId = cityId)
        }

    override suspend fun getCity(url: String): Flow<DataState<CityInfoResponse>> =
        getResult {
            cityService.getCity(url)
        }


    override suspend fun getFavoriteList(): List<CityFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: CityFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<CityFavoriteEntity>) = dao.insert(entityList)

}
