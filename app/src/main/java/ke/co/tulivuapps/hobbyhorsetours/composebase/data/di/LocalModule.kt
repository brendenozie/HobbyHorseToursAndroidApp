package ke.co.tulivuapps.hobbyhorsetours.composebase.data.di

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.room.Room
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.db.HobbyHorseToursDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by brendenozie on 27.03.2023
 */
@Stable
@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideHobbyHorseToursDatabase(
        @ApplicationContext context: Context
    ): HobbyHorseToursDatabase {
        return HobbyHorseToursDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: HobbyHorseToursDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }
}