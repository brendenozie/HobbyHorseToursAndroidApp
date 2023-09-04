package ke.co.tulivuapps.hobbyhorsetours.composebase.data.di

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.HotelService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.HotelRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl.HotelRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository.HotelRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.HotelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.HotelDao
import retrofit2.Retrofit

/**
 * Created by brendenozie on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class HotelModule {
    @Provides
    fun provideHotelService(retrofit: Retrofit): HotelService =
        retrofit.create(HotelService::class.java)

    @Provides
    fun provideHotelRemoteDataSource(
        hotelService: HotelService,
        dao: HotelDao
    ): HotelRemoteDataSource =
        HotelRemoteDataSourceImpl(hotelService, dao)

    @Provides
    fun provideHotelRepository(
        accountRemoteDataSource: HotelRemoteDataSource,
        dao: HotelDao
    ): HotelRepository =
        HotelRepositoryImpl(accountRemoteDataSource, dao)
}