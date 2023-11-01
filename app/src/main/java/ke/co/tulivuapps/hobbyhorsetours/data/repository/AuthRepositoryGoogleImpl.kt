package ke.co.tulivuapps.hobbyhorsetours.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.AuthRepositoryGoogle
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.await
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class AuthRepositoryGoogleImpl() : AuthRepositoryGoogle {

    override suspend fun loginWithGoogle(idToken: String): Unit = withContext(IO) {
        Firebase.auth
            .signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .await()
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