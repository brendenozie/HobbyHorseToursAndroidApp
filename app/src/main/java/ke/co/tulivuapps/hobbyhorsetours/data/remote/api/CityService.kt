package ke.co.tulivuapps.hobbyhorsetours.data.remote.api

import ke.co.tulivuapps.hobbyhorsetours.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.city.CityResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by brendenozie on 10.03.2023
 */

interface CityService {

    @GET(Constants.CITY_LIST)
    suspend fun getAllCitys(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<CityResponse>

    @GET(Constants.GET_CITY)
    suspend fun getCity(
        @Path(Constants.PARAM_ID) cityId: Int
    ): Response<CityInfoResponse>

    @GET(Constants.GET_CITY)
    suspend fun getCity(
        @Url url: String
    ): Response<CityInfoResponse>

    @GET(Constants.CITY_FILTER)
    suspend fun getFilterCity(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<CityResponse>
}