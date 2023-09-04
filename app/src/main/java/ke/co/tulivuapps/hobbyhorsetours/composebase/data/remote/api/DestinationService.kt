package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.destination.DestinationInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.destination.DestinationResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by brendenozie on 10.03.2023
 */

interface DestinationService {

    @GET(Constants.DESTINATION_LIST)
    suspend fun getAllDestinations(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<DestinationResponse>

    @GET(Constants.GET_DESTINATION)
    suspend fun getDestination(
        @Path(Constants.PARAM_ID) characterId: Int
    ): Response<DestinationInfoResponse>

    @GET(Constants.GET_DESTINATION)
    suspend fun getDestination(
        @Url url: String
    ): Response<DestinationInfoResponse>

    @GET(Constants.DESTINATION_FILTER)
    suspend fun getFilterDestination(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<DestinationResponse>
}