package ke.co.tulivuapps.hobbyhorsetours.data.remote.api

import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by brendenozie on 19.03.2023
 */
interface PopularService {
    @GET(Constants.GET_POPULAR)
    suspend fun getAllPopular(): Response<PopularResponse>

    @GET(Constants.GET_EPISODE)
    suspend fun getPopular(
        @Path(Constants.PARAM_ID) episodeId: Int
    ): Response<ResultPopular>

}