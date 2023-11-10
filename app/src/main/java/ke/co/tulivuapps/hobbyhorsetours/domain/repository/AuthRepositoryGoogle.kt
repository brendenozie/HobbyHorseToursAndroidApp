package ke.co.tulivuapps.hobbyhorsetours.domain.repository

import com.google.firebase.auth.AuthCredential
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepositoryGoogle {

    suspend fun loginWithGoogle(idToken: String): Unit
    suspend fun signUpWithGooogle(idToken: String): Flow<DataState<UserInfoResponse>>

    suspend fun signUpWithEmail(email: String, password: String): Unit

    suspend fun signInWithEmail(email: String, password: String): Unit

    suspend fun sendPasswordResetEmail(email: String): Unit

    suspend fun signInWithPhoneNumber(credential: AuthCredential): Unit

}