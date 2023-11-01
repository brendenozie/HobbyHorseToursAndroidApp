package ke.co.tulivuapps.hobbyhorsetours.domain.repository

import com.google.firebase.auth.AuthCredential

interface AuthRepositoryGoogle {

    suspend fun loginWithGoogle(idToken: String): Unit

    suspend fun signUpWithEmail(email: String, password: String): Unit

    suspend fun signInWithEmail(email: String, password: String): Unit

    suspend fun sendPasswordResetEmail(email: String): Unit

    suspend fun signInWithPhoneNumber(credential: AuthCredential): Unit

}