package ke.co.tulivuapps.hobbyhorsetours.data.repository

import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.AuthRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.AuthRemoteRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class AuthRemoteRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource) : AuthRemoteRepository {

    override suspend fun loginWithCredentials(userLoginInfoResponse: UserLoginInfoResponse) = flow {
        emitAll(authRemoteDataSource.getLoginDetails(userLoginInfoResponse))
    }

    override suspend fun signUpWithCredentials(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        TODO("Not yet implemented")
    }

}