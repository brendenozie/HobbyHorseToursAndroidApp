package ke.co.tulivuapps.hobbyhorsetours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.BookingService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.BookingsRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl.BookingRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.data.repository.BookingsRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.BookingsRepository
import retrofit2.Retrofit

/**
 * Created by brendenozie on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class BookingsModule {
    @Provides
    fun provideBookingService(retrofit: Retrofit): BookingService =
        retrofit.create(BookingService::class.java)

    @Provides
    fun provideBookingRemoteDataSource(
        cityService: BookingService,
    ): BookingsRemoteDataSource =
        BookingRemoteDataSourceImpl(cityService)

    @Provides
    fun provideBookingRepository(
        accountRemoteDataSource: BookingsRemoteDataSource,
    ): BookingsRepository =
        BookingsRepositoryImpl(accountRemoteDataSource)
}