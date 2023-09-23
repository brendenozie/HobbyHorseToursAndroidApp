package ke.co.tulivuapps.hobbyhorsetours.data.local

import kotlinx.coroutines.flow.Flow

interface DataStoreOperation {

    suspend fun saveOnBoardingState(complete: Boolean)
    fun readOnBoardingState(): Flow<Boolean>

    suspend fun saveOnLoginState(complete: Boolean)
    fun readOnLoginState(): Flow<Boolean>

    suspend fun saveToken(token: String)
    fun getToken(): Flow<String>

}