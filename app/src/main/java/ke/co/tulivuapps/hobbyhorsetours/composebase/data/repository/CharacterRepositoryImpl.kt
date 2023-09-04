package ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.character.CharacterInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.character.CharacterResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.CharacterRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val dao: FavoriteDao
) : CharacterRepository {

    override suspend fun getAllCharacters(
        page: Int,
        options: Map<String, String>
    ): Response<CharacterResponse> =
        characterRemoteDataSource.getAllCharacters(page = page, options = options)

    override fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getCharacter(characterId = characterId))
    }

    override fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getCharacter(url = url))
    }

    override suspend fun getFilterCharacters(
        page: Int,
        options: Map<String, String>
    ): Response<CharacterResponse> = characterRemoteDataSource.getFilterCharacters(page, options)

    override suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getFavoriteList(): List<FavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: FavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<FavoriteEntity>) = dao.insert(entityList)
}