package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.HotelFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.hotel.HotelInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.hotel.HotelResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface HotelRemoteDataSource {
    suspend fun getAllHotels(page: Int, options: Map<String, String>): Response<HotelResponse>
    suspend fun getFilterHotels(page: Int, options: Map<String, String>): Response<HotelResponse>
    suspend fun getHotel(characterId: Int): Flow<DataState<HotelInfoResponse>>
    suspend fun getHotel(url: String): Flow<DataState<HotelInfoResponse>>
    suspend fun getFavoriteList(): List<HotelFavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): HotelFavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int)
    suspend fun deleteFavoriteList()
    suspend fun saveFavorite(entity: HotelFavoriteEntity)
    suspend fun saveFavoriteList(entityList: List<HotelFavoriteEntity>)

}