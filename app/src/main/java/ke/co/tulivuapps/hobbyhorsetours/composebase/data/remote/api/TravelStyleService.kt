package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelstyle.TravelStyleInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelstyle.TravelStyleResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by brendenozie on 10.03.2023
 */

interface TravelStyleService {

    @GET(Constants.TRAVEL_STYLE_LIST)
    suspend fun getAllTravelStyles(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<TravelStyleResponse>

    @GET(Constants.GET_TRAVEL_STYLE)
    suspend fun getTravelStyle(
        @Path(Constants.PARAM_ID) travelStyleId: Int
    ): Response<TravelStyleInfoResponse>

    @GET(Constants.GET_TRAVEL_STYLE)
    suspend fun getTravelStyle(
        @Url url: String
    ): Response<TravelStyleInfoResponse>

    @GET(Constants.TRAVEL_STYLE_FILTER)
    suspend fun getFilterTravelStyle(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<TravelStyleResponse>
}