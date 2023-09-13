package ke.co.tulivuapps.hobbyhorsetours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.HotelDao
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.HotelService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.HotelRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl.HotelRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.data.repository.HotelRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.HotelRepository
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