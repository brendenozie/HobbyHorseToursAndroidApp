package ke.co.tulivuapps.hobbyhorsetours.composebase.data.di

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.CharacterService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.CharacterRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl.CharacterRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository.CharacterRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by brendenozie on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class CharacterModule {
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    @Provides
    fun provideCharacterRemoteDataSource(
        characterService: CharacterService,
        dao: FavoriteDao
    ): CharacterRemoteDataSource =
        CharacterRemoteDataSourceImpl(characterService, dao)

    @Provides
    fun provideCharacterRepository(
        accountRemoteDataSource: CharacterRemoteDataSource,
        dao: FavoriteDao
    ): CharacterRepository =
        CharacterRepositoryImpl(accountRemoteDataSource, dao)
}