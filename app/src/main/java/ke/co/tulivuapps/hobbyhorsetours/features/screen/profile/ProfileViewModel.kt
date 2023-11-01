package ke.co.tulivuapps.hobbyhorsetours.features.screen.profile

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city.GetCityUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city.UpdateCityFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations.GetDestinationsUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations.UpdateDestinationFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.GetHotelsUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.UpdateHotelFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle.GetTravelStyleUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle.UpdateTravelStyleFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.profile.ProfileViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDestinationsUseCase: GetDestinationsUseCase,
    private val updateDestinationFavoriteUseCase: UpdateDestinationFavoriteUseCase,
    private val getcityUseCase: GetCityUseCase,
    private val updateCityFavoriteUseCase: UpdateCityFavoriteUseCase,
    private val getTravelStyleUseCase: GetTravelStyleUseCase,
    private val updateTravelStyleFavoriteUseCase: UpdateTravelStyleFavoriteUseCase,
    private val getHotelsUseCase: GetHotelsUseCase,
    private val updateHotelFavoriteUseCase: UpdateHotelFavoriteUseCase,
    private val dataStoreOperation: DataStoreOperation
) : BaseViewModel<ProfileViewState, ProfileViewEvent>() {

    private val config = PagingConfig(pageSize = 10)

    val selectedLogin =  dataStoreOperation.readOnLoginState().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    val selectedUsername =  dataStoreOperation.readNameState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )

    val selectedEmail =  dataStoreOperation.readEmailState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )

    val selectedImg =  dataStoreOperation.readImageState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )


    init {

        }

    override fun createInitialState() = ProfileViewState()

    override fun onTriggerEvent(event: ProfileViewEvent) {
        viewModelScope.launch {
            when (event) {
                is ProfileViewEvent.UpdateHomeFavorite -> {}
            }
        }
    }
}

sealed class ProfileViewEvent : IViewEvent {
    class UpdateHomeFavorite(val dto: CityDto) : ProfileViewEvent()
}