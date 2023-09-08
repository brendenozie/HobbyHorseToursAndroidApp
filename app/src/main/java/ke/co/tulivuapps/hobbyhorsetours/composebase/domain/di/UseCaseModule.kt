package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CharacterRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CityRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.DestinationRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.HotelRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.TravelStyleRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.characters.GetCharactersFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.characters.GetCharactersUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city.DeleteCityFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city.GetCityFavoritesUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city.GetCityFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city.GetCityUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city.UpdateCityFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.DeleteDestinationFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.GetDestinationFavoritesUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.GetDestinationsFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.GetDestinationsUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.UpdateDestinationFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.favorite.DeleteFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.favorite.GetFavoritesUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.favorite.UpdateFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.DeleteHotelFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.GetHotelFavoritesUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.GetHotelsFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.GetHotelsUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.UpdateHotelFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle.DeleteTravelStyleFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle.GetTravelStyleFavoritesUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle.GetTravelStyleFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle.GetTravelStyleUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle.UpdateTravelStyleFavoriteUseCase

/**
 * Created by brendenozie on 27.03.2023
 */

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetCharactersUseCase(
        characterRepository: CharacterRepository
    ) = GetCharactersUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideUpdateFavoriteUseCase(
        characterRepository: CharacterRepository
    ) = UpdateFavoriteUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteFavoriteUseCase(
        characterRepository: CharacterRepository
    ) = DeleteFavoriteUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideGetFavoritesUseCase(
        characterRepository: CharacterRepository
    ) = GetFavoritesUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideGetCharactersFilterUseCase(
        characterRepository: CharacterRepository
    ) = GetCharactersFilterUseCase(characterRepository)

    // Destination UseCase Module

    @ViewModelScoped
    @Provides
    fun provideGetDestinationsUseCase(
        destinationRepository: DestinationRepository
    ) = GetDestinationsUseCase(destinationRepository)

    @ViewModelScoped
    @Provides
    fun provideUpdateDestinationFavoriteUseCase(
        destinationRepository: DestinationRepository
    ) = UpdateDestinationFavoriteUseCase(destinationRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteDestinationFavoriteUseCase(
        destinationRepository: DestinationRepository
    ) = DeleteDestinationFavoriteUseCase(destinationRepository)

    @ViewModelScoped
    @Provides
    fun provideGetDestinationFavoritesUseCase(
        destinationRepository: DestinationRepository
    ) = GetDestinationFavoritesUseCase(destinationRepository)

    @ViewModelScoped
    @Provides
    fun provideGetDestinationsFilterUseCase(
        destinationRepository: DestinationRepository
    ) = GetDestinationsFilterUseCase(destinationRepository)

    // Hotel UseCase Module

    @ViewModelScoped
    @Provides
    fun provideGetHotelsUseCase(
        hotelRepository: HotelRepository
    ) = GetHotelsUseCase(hotelRepository)

    @ViewModelScoped
    @Provides
    fun provideUpdateHotelFavoriteUseCase(
        hotelRepository: HotelRepository
    ) = UpdateHotelFavoriteUseCase(hotelRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteHotelFavoriteUseCase(
        hotelRepository: HotelRepository
    ) = DeleteHotelFavoriteUseCase(hotelRepository)

    @ViewModelScoped
    @Provides
    fun provideGetHotelFavoritesUseCase(
        hotelRepository: HotelRepository
    ) = GetHotelFavoritesUseCase(hotelRepository)

    @ViewModelScoped
    @Provides
    fun provideGetHotelsFilterUseCase(
        hotelRepository: HotelRepository
    ) = GetHotelsFilterUseCase(hotelRepository)


    // City UseCase Module

    @ViewModelScoped
    @Provides
    fun provideGetCityUseCase(
        cityRepository: CityRepository
    ) = GetCityUseCase(cityRepository)

    @ViewModelScoped
    @Provides
    fun provideUpdateCityFavoriteUseCase(
        cityRepository: CityRepository
    ) = UpdateCityFavoriteUseCase(cityRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteCityFavoriteUseCase(
        cityRepository: CityRepository
    ) = DeleteCityFavoriteUseCase(cityRepository)

    @ViewModelScoped
    @Provides
    fun provideGetCityFavoritesUseCase(
        cityRepository: CityRepository
    ) = GetCityFavoritesUseCase(cityRepository)

    @ViewModelScoped
    @Provides
    fun provideGetCityFilterUseCase(
        cityRepository: CityRepository
    ) = GetCityFilterUseCase(cityRepository)


    // TravelStyle UseCase Module

    @ViewModelScoped
    @Provides
    fun provideGetTravelStyleUseCase(
        travelStyleRepository: TravelStyleRepository
    ) = GetTravelStyleUseCase(travelStyleRepository)

    @ViewModelScoped
    @Provides
    fun provideUpdateTravelStyleFavoriteUseCase(
        travelStyleRepository: TravelStyleRepository
    ) = UpdateTravelStyleFavoriteUseCase(travelStyleRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteTravelStyleFavoriteUseCase(
        travelStyleRepository: TravelStyleRepository
    ) = DeleteTravelStyleFavoriteUseCase(travelStyleRepository)

    @ViewModelScoped
    @Provides
    fun provideGetTravelStyleFavoritesUseCase(
        travelStyleRepository: TravelStyleRepository
    ) = GetTravelStyleFavoritesUseCase(travelStyleRepository)

    @ViewModelScoped
    @Provides
    fun provideGetTravelStyleFilterUseCase(
        travelStyleRepository: TravelStyleRepository
    ) = GetTravelStyleFilterUseCase(travelStyleRepository)
}