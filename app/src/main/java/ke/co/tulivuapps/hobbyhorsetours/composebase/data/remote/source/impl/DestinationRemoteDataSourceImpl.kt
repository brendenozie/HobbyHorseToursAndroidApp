package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.DestinationDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.DestinationFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.destination.DestinationInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.destination.DestinationResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.DestinationService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.DestinationRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */
class DestinationRemoteDataSourceImpl @Inject constructor(
    private val characterService: DestinationService,
    private val dao: DestinationDao
) :
    BaseRemoteDataSource(), DestinationRemoteDataSource {

    override suspend fun getFavorite(favoriteId: Int): DestinationFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getAllDestinations(
        page: Int,
        options: Map<String, String>
    ): Response<DestinationResponse> = characterService.getAllDestinations(page, options)

    override suspend fun getFilterDestinations(
        page: Int, options: Map<String, String>
    ): Response<DestinationResponse> = characterService.getFilterDestination(page, options)

    override suspend fun getDestination(characterId: Int): Flow<DataState<DestinationInfoResponse>> =
        getResult { characterService.getDestination(characterId = characterId) }

    override suspend fun getDestination(url: String): Flow<DataState<DestinationInfoResponse>> =
        getResult {
            characterService.getDestination(url)
        }


    override suspend fun getFavoriteList(): List<DestinationFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: DestinationFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<DestinationFavoriteEntity>) = dao.insert(entityList)

}
