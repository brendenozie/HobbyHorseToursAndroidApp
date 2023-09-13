package ke.co.tulivuapps.hobbyhorsetours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.CityDao
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.CityService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.CityRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl.CityRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.data.repository.CityRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.CityRepository
import retrofit2.Retrofit

/**
 * Created by brendenozie on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class CityModule {
    @Provides
    fun provideCityService(retrofit: Retrofit): CityService =
        retrofit.create(CityService::class.java)
    @Provides
    fun provideCityRemoteDataSource(
        cityService: CityService,
        dao: CityDao
    ): CityRemoteDataSource =
        CityRemoteDataSourceImpl(cityService, dao)
    @Provides
    fun provideCityRepository(
        accountRemoteDataSource: CityRemoteDataSource,
        dao: CityDao
    ): CityRepository =
        CityRepositoryImpl(accountRemoteDataSource, dao)
}