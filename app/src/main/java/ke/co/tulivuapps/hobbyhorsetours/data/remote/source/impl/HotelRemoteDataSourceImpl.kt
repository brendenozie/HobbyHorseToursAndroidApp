package ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.HotelDao
import ke.co.tulivuapps.hobbyhorsetours.data.model.hotel.HotelFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.data.model.hotel.HotelInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.hotel.HotelResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.HotelService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.HotelRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */
class HotelRemoteDataSourceImpl @Inject constructor(
    private val characterService: HotelService,
    private val dao: HotelDao
) :
    BaseRemoteDataSource(), HotelRemoteDataSource {

    override suspend fun getFavorite(favoriteId: Int): HotelFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getAllHotels(
        page: Int,
        options: Map<String, String>
    ): Response<HotelResponse> = characterService.getAllHotels(page, options)

    override suspend fun getFilterHotels(
        page: Int, options: Map<String, String>
    ): Response<HotelResponse> = characterService.getFilterHotel(page, options)

    override suspend fun getHotel(characterId: Int): Flow<DataState<HotelInfoResponse>> =
        getResult { characterService.getHotel(characterId = characterId) }

    override suspend fun getHotel(url: String): Flow<DataState<HotelInfoResponse>> =
        getResult {
            characterService.getHotel(url)
        }


    override suspend fun getFavoriteList(): List<HotelFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: HotelFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<HotelFavoriteEntity>) = dao.insert(entityList)

}
