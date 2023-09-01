package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.EpisodesResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by brendenozie on 19.03.2023
 */
interface EpisodesService {

    @GET(Constants.EPISODE_LIST)
    suspend fun getAllEpisodes(): Response<EpisodesResponse>

    @GET(Constants.GET_EPISODE)
    suspend fun getEpisode(
        @Path(Constants.PARAM_ID) episodeId: Int
    ): Response<EpisodesResponse>

}