package ke.co.tulivuapps.hobbyhorsetours.data.di

import android.content.Context
import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.CityDao
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.DestinationDao
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.HotelDao
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.TravelStyleDao
import ke.co.tulivuapps.hobbyhorsetours.data.local.db.HobbyHorseToursDatabase
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
    @Provides
    @Singleton
    fun provideHotelDao(appDatabase: HobbyHorseToursDatabase): HotelDao {
        return appDatabase.hotelDao()
    }
    @Provides
    @Singleton
    fun provideDestinationDao(appDatabase: HobbyHorseToursDatabase): DestinationDao {
        return appDatabase.destinationDao()
    }
    @Provides
    @Singleton
    fun provideCityDao(appDatabase: HobbyHorseToursDatabase): CityDao {
        return appDatabase.cityDao()
    }
    @Provides
    @Singleton
    fun provideTravelStyleDao(appDatabase: HobbyHorseToursDatabase): TravelStyleDao {
        return appDatabase.travelStyleDao()
    }
}