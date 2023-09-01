package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.CharacterInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.CharacterResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface CharacterRepository {
    suspend fun getAllCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
    fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>>
    fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>>
    suspend fun getFilterCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
    suspend fun getFavoriteList(): List<FavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int): Unit
    suspend fun deleteFavoriteList(): Unit
    suspend fun saveFavorite(entity: FavoriteEntity): Unit
    suspend fun saveFavoriteList(entityList: List<FavoriteEntity>): Unit
}