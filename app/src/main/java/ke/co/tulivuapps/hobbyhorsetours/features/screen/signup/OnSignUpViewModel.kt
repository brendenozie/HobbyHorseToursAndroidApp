package ke.co.tulivuapps.hobbyhorsetours.features.screen.signup

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
class OnSignUpViewModel @Inject constructor(
    private val dataStoreOperation: DataStoreOperation
) : ViewModel() {

    private val _onLoginCompleted = MutableStateFlow(false)
    val onLoginCompleted: StateFlow<Boolean> = _onLoginCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onLoginCompleted.value =
                dataStoreOperation.readOnLoginState().stateIn(viewModelScope).value

        }
    }


}