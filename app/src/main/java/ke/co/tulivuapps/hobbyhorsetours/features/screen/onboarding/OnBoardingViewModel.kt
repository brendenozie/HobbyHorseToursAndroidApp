package ke.co.tulivuapps.hobbyhorsetours.features.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataStoreOperation: DataStoreOperation
) : ViewModel() {

    val onBoarded =  dataStoreOperation.readOnBoardingState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000),
        false
    )

    fun setOnboarding() {
        viewModelScope.launch {
            dataStoreOperation.saveOnBoardingState(true)
        }
    }
}