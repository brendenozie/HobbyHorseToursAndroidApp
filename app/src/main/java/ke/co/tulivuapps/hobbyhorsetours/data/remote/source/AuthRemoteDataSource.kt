package ke.co.tulivuapps.hobbyhorsetours.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 12.03.2023
 */

interface AuthRemoteDataSource {
    suspend fun getLoginDetails(data: UserLoginInfoResponse): Flow<DataState<UserInfoResponse>>
    suspend fun getRegistrationDetails(data:UserInfoResponse): Flow<DataState<UserInfoResponse>>

}