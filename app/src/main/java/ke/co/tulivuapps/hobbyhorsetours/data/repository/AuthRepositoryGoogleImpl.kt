package ke.co.tulivuapps.hobbyhorsetours.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ke.co.tulivuapps.hobbyhorsetours.data.model.APIError
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.ResultUser
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.AuthRepositoryGoogle
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.await
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AuthRepositoryGoogleImpl() : AuthRepositoryGoogle {

    override suspend fun loginWithGoogle(idToken: String): Flow<DataState<UserInfoResponse>> = flow {
        val usr = Firebase.auth
            .signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .await().user

        if (usr != null) {
            emit(
                DataState.Success(
                    UserInfoResponse(
                        "200",
                        ResultUser("",
                            usr.displayName?:"nothing"
                            , usr.email?:"nothing"
                            , usr.photoUrl?.toString()
                                ?:"nothing"),
                        "Success"
                    )
                )
            )
        } else {
            val apiError: APIError =APIError(404,"ERROR NOTHING")
            emit(DataState.Error(apiError))
        }
    }
    override suspend fun signUpWithGooogle(idToken: String) : Flow<DataState<UserInfoResponse>> = flow {
        val usr = Firebase.auth
            .signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .await().user

        if (usr != null) {
            emit(
                DataState.Success(
                    UserInfoResponse(
                        "200",
                        ResultUser("",
                            usr.displayName?:"nothing"
                            , usr.email?:"nothing"
                            , usr.photoUrl?.toString()
                                ?:"nothing"),
                        "Success"
                    )
                )
            )
        } else {
            val apiError: APIError =APIError(404,"ERROR NOTHING")
            emit(DataState.Error(apiError))
        }
    }
    override suspend fun signUpWithEmail(email: String, password: String): Unit = withContext(IO) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun signInWithEmail(email: String, password: String): Unit = withContext(IO) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun sendPasswordResetEmail(email: String): Unit = withContext(IO) {
        Firebase.auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun signInWithPhoneNumber(credential: AuthCredential): Unit = withContext(IO) {
        Firebase.auth.signInWithCredential(credential).await()
    }

}