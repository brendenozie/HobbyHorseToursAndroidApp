package ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.base.BaseDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.HotelFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.Constants

/**
 * Created by brendenozie on 27.03.2023
 */

@Dao
interface HotelDao : BaseDao<HotelFavoriteEntity> {
    @Query("SELECT * FROM ${Constants.HOTEL_TABLE_NAME}")
    suspend fun getFavoriteList(): List<HotelFavoriteEntity>

    @Query("SELECT * FROM ${Constants.HOTEL_TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): HotelFavoriteEntity?

    @Query("DELETE FROM ${Constants.HOTEL_TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${Constants.HOTEL_TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}