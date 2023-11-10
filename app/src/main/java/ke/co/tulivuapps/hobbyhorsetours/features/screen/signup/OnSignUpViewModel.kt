package ke.co.tulivuapps.hobbyhorsetours.features.screen.signup

import android.app.Application
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.ResultUser
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.SignupRequest
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserSignupInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.AuthRemoteRepository
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.AuthRepositoryGoogle
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.signup.SignupViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnSignUpViewModel @Inject constructor(
    private val app: Application,
    private val authRepositoryGoogle: AuthRepositoryGoogle,
    private val authRemoteRepository: AuthRemoteRepository,
    private val dataStoreOperation: DataStoreOperation
) : BaseViewModel<SignupViewState, SignUpViewEvent>() {


    //note: You have to add SHA1 for google login to work.

    //to get SHA1 for debug mode, run this: keytool -list -v -keystore <debug keystore path> -alias androiddebugkey -storepass android -keystore android
    //debug keystore path example: C:\Users\sheri\.android\debug.keystore (replace "\Users\sheri\" with your username on your computer.

    //to get SHA1 for release mode, run this: keytool -list -v -alias keytstore -keystore <keystore path> -alias <alias name>
    //keystore path example: F:\Ameen\upload-keystore.jks
    //keystore alias name example: upload

//    private val _token = MutableStateFlow("")
//    val toke: StateFlow<String> = _token

    private val _onSignUpCompleted = MutableStateFlow(false)
    val onSignUpCompleted: StateFlow<Boolean> = _onSignUpCompleted

//    private val _loading = MutableStateFlow(false)
//    val loading: StateFlow<Boolean> = _loading

    private val _user = MutableStateFlow<DataState<UserInfoResponse>?>(null)
    val user: MutableStateFlow<DataState<UserInfoResponse>?> = _user

    private val _resultUser = MutableStateFlow<ResultUser?>(null)
    val resultUser: MutableStateFlow<ResultUser?> = _resultUser

    private val _userDetails = MutableStateFlow<UserInfoResponse?>(null)
    val userDetails: MutableStateFlow<UserInfoResponse?> = _userDetails

    private val _signedIn = MutableStateFlow(false)

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val selectedLogin =  dataStoreOperation.readOnLoginState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun removeError() {
        _error.value = null
    }

    fun signUpWithCredentials(username:String, emailAddress: String, password:String, img:String="") = viewModelScope.launch() {
        setState { currentState.copy(isLoading = false) }
        try {
            authRemoteRepository.signUpWithCredentials(
                UserSignupInfoResponse(
                    SignupRequest(
                        name = username,
                        email = emailAddress,
                        password = password,
                        img=img
                    )
                )
            ).stateIn(viewModelScope).collect{
                when (it) {
                    is DataState.Success -> {
                        setState { currentState.copy(data = it.data.body, isLoading = false) }
                        _userDetails.value = it.data
                        dataStoreOperation.saveOnLoginState(true)
                        it.data.body?.let { it1 -> dataStoreOperation.saveNameState(it1.name) }
                        it.data.body?.let { it1 -> dataStoreOperation.saveEmailState(it1.email) }
                        it.data.body?.let { it1 -> dataStoreOperation.saveImageState(it1.image) }
                        _signedIn.value = true
                        _onSignUpCompleted.value = true
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        setEvent(SignUpViewEvent.SnackBarError(it.apiError?.message))
                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }
                }
            }
        } catch (e: Exception) {
            _error.value = e.localizedMessage
            setState { currentState.copy(isLoading = false) }
        } finally {
            setState { currentState.copy(isLoading = false) }
        }
    }

    fun signupWithGoogle(idToken: String) = viewModelScope.launch {
        setState { currentState.copy(isLoading = false) }
        try {
            authRepositoryGoogle.signUpWithGooogle(idToken)
            .stateIn(viewModelScope).collect{
                when (it) {
                    is DataState.Success -> {
                        setState { currentState.copy(isLoading = false,data = it.data.body) }
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        setEvent(SignUpViewEvent.SnackBarError(it.apiError?.message))
                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }
                }
            }
        } catch (e: Exception) {
            _error.value = e.localizedMessage
            setState { currentState.copy(isLoading = false) }
        } finally {
            setState { currentState.copy(isLoading = false) }
        }
//        try {
//            app.connectedOrThrow()
//            _loading.value = true
//            authRepositoryGoogle.loginWithGoogle(idToken)
//            dataStoreOperation.saveOnLoginState(true)
//            _signedIn.value = true
//        } catch (e: Exception) {
//            _error.value = e.localizedMessage
//        } finally {
//            _loading.value = false
//        }
    }

//    fun updateToken(toke: String) {
//        _token.value = toke
//    }

    private fun updateHotelFavorite(dto: String) = viewModelScope.launch {
//        val params = UpdateHotelFavoriteUseCase.Params(dto)
//        call(updateHotelFavoriteUseCase(params))
    }

    override fun createInitialState() = SignupViewState()

    override fun onTriggerEvent(event: SignUpViewEvent) {
        viewModelScope.launch {
            when (event) {
                is SignUpViewEvent.UpdateHotelFavorite -> updateHotelFavorite(event.dto)
                is SignUpViewEvent.SnackBarError -> {}
                else -> {}
            }
        }
    }
}

sealed class SignUpViewEvent : IViewEvent {
    class UpdateHotelFavorite(val dto: String) : SignUpViewEvent()
    class SnackBarError(val message: String?) : SignUpViewEvent()
}