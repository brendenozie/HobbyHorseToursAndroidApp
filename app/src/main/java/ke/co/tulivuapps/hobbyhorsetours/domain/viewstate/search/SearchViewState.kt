package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.search

import android.os.Parcelable
import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 9.04.2023
 */

@Stable
data class SearchViewState(
    val searchText: String? = null,
    val pagedHotelData: Flow<PagingData<HotelDto>>? = null,
    val pagedDestinationData: Flow<PagingData<DestinationDto>>? = null,
    val pagedCityData: Flow<PagingData<CityDto>>? = null,
    val selectedCityData: CityDto? = null,
    val pagedTravelStyleData: Flow<PagingData<TravelStyleDto>>? = null,
    val selectedTravelStyleData: TravelStyleDto? = null,
    val isLoading: Boolean = false
) : IViewState

@Parcelize
data class CharacterStatus(
    val name: String,
    val selected: Boolean,
) : Parcelable

@Parcelize
data class CharacterGender(
    val name: String,
    val selected: Boolean,
) : Parcelable
