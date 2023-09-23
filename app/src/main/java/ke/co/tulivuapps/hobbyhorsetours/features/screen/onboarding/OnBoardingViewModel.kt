package ke.co.tulivuapps.hobbyhorsetours.features.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataStoreOperation: DataStoreOperation
) : ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value =dataStoreOperation.readOnBoardingState().stateIn(viewModelScope).value
        }
    }


    fun setOnboarding() {
        viewModelScope.launch {
            dataStoreOperation.saveOnBoardingState(true)
        }
    }
}