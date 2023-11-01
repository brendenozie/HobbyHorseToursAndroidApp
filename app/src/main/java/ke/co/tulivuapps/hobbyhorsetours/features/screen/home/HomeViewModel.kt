package ke.co.tulivuapps.hobbyhorsetours.features.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreOperation: DataStoreOperation
) : ViewModel() {


    private val _signedIn = MutableStateFlow(Firebase.auth.currentUser != null)
    val signedIn: StateFlow<Boolean> = _signedIn

    private val _onLoginCompleted = MutableStateFlow(false)
    val onLoginCompleted: StateFlow<Boolean> = _onLoginCompleted

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onLoginCompleted.value =  dataStoreOperation.readOnLoginState().stateIn(viewModelScope).value

            _userName.value =  dataStoreOperation.readNameState().stateIn(viewModelScope).value

        }

//        Firebase.auth.addAuthStateListener {
//            _signedIn.value = it.currentUser != null
//        }
    }


    fun signOut() = Firebase.auth.signOut()


}