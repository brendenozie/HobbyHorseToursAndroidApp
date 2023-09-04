package ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.DestinationDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.DestinationFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.destination.DestinationInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.destination.DestinationResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.DestinationRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */

class DestinationRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: DestinationRemoteDataSource,
    private val dao: DestinationDao
) : DestinationRepository {

    override suspend fun getAllDestinations(
        page: Int,
        options: Map<String, String>
    ): Response<DestinationResponse> =
        characterRemoteDataSource.getAllDestinations(page = page, options = options)

    override fun getDestination(characterId: Int): Flow<DataState<DestinationInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getDestination(characterId = characterId))
    }

    override fun getDestination(url: String): Flow<DataState<DestinationInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getDestination(url = url))
    }

    override suspend fun getFilterDestinations(
        page: Int,
        options: Map<String, String>
    ): Response<DestinationResponse> = characterRemoteDataSource.getFilterDestinations(page, options)

    override suspend fun getFavorite(favoriteId: Int): DestinationFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getFavoriteList(): List<DestinationFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: DestinationFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<DestinationFavoriteEntity>) = dao.insert(entityList)
}