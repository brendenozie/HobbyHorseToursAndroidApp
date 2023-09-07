package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.city.CityResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.CityService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.CityRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */
class CityRemoteDataSourceImpl @Inject constructor(
    private val cityService: CityService,
    private val dao: FavoriteDao
) :
    BaseRemoteDataSource(), CityRemoteDataSource {

    override suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getAllCitys(
        page: Int,
        options: Map<String, String>
    ): Response<CityResponse> = cityService.getAllCitys(page, options)

    override suspend fun getFilterCitys(
        page: Int, options: Map<String, String>
    ): Response<CityResponse> = cityService.getFilterCity(page, options)

    override suspend fun getCity(cityId: Int): Flow<DataState<CityInfoResponse>> =
        getResult { cityService.getCity(cityId = cityId) }

    override suspend fun getCity(url: String): Flow<DataState<CityInfoResponse>> =
        getResult {
            cityService.getCity(url)
        }


    override suspend fun getFavoriteList(): List<FavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: FavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<FavoriteEntity>) = dao.insert(entityList)

}
