package ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.AuthService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.AuthRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */
class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) :   BaseRemoteDataSource(), AuthRemoteDataSource {

    override suspend fun getLoginDetails(data:UserLoginInfoResponse): Flow<DataState<UserInfoResponse>> = getResult {
        authService.postLoginDetails(data)
    }

    override suspend fun getRegistrationDetails(data:UserInfoResponse): Flow<DataState<UserInfoResponse>> = getResult {
        authService.postRegistrationDetails(data)
    }


}
