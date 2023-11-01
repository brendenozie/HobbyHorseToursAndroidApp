package ke.co.tulivuapps.hobbyhorsetours.data.local

import kotlinx.coroutines.flow.Flow

interface DataStoreOperation {

    suspend fun saveOnBoardingState(complete: Boolean)
    fun readOnBoardingState(): Flow<Boolean>

    suspend fun saveOnLoginState(complete: Boolean)
    fun readOnLoginState(): Flow<Boolean>

    suspend fun saveEmailState(email: String)
    fun readEmailState(): Flow<String>
    suspend fun saveNameState(name: String)
    fun readNameState(): Flow<String>
    suspend fun saveImageState(image: String)
    fun readImageState(): Flow<String>

    suspend fun saveToken(token: String)
    fun getToken(): Flow<String>

}