package ke.co.tulivuapps.hobbyhorsetours.domain.repository

import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserSignupInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRemoteRepository {

    suspend fun loginWithCredentials(userLoginInfoResponse: UserLoginInfoResponse): Flow<DataState<UserInfoResponse>>

    suspend fun signUpWithCredentials(userSignupInfoResponse: UserSignupInfoResponse): Flow<DataState<UserInfoResponse>>

    suspend fun sendPasswordResetEmail(email: String): Unit

}