package ke.co.tulivuapps.hobbyhorsetours.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants.DATASTORE_NAME
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants.PREFERENCES_BOARDING_KEY
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants.PREFERENCES_EMAIL_KEY
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants.PREFERENCES_IMAGE_KEY
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants.PREFERENCES_LOGIN_KEY
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants.PREFERENCES_NAME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)

class DataStoreOperationImpl(context: Context) : DataStoreOperation {

    private val dataStore = context.dataStore

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(PREFERENCES_BOARDING_KEY)
        val onLoginKey = booleanPreferencesKey(PREFERENCES_LOGIN_KEY)
        val onEmailKey = stringPreferencesKey(PREFERENCES_EMAIL_KEY)
        val onNameKey = stringPreferencesKey(PREFERENCES_NAME_KEY)
        val onImageKey = stringPreferencesKey(PREFERENCES_IMAGE_KEY)
        val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    override suspend fun saveOnBoardingState(complete: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = complete
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
            onBoardingState
        }
    }

    override suspend fun saveOnLoginState(complete: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onLoginKey] = complete
        }
    }

    override fun readOnLoginState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onLoginState = preferences[PreferencesKey.onLoginKey] ?: false
            onLoginState
        }
    }

    override suspend fun saveEmailState(email: String) {
        dataStore.edit { prefs ->
            prefs[PreferencesKey.onEmailKey] = email
        }
    }

    override fun readEmailState(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onLoginState = preferences[PreferencesKey.onEmailKey] ?: ""
            onLoginState
        }
    }

    override suspend fun saveNameState(name: String) {
        dataStore.edit { prefs ->
            prefs[PreferencesKey.onNameKey] = name
        }
    }

    override fun readNameState(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onLoginState = preferences[PreferencesKey.onNameKey] ?: ""
            onLoginState
        }
    }

    override suspend fun saveImageState(image: String) {
        dataStore.edit { prefs ->
            prefs[PreferencesKey.onImageKey] = image
        }
    }

    override fun readImageState(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onLoginState = preferences[PreferencesKey.onImageKey] ?: ""
            onLoginState
        }
    }


    override suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[PreferencesKey.KEY_ACCESS_TOKEN] = token
        }
    }

    override fun getToken(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[PreferencesKey.KEY_ACCESS_TOKEN] ?: ""
        }

    }
}