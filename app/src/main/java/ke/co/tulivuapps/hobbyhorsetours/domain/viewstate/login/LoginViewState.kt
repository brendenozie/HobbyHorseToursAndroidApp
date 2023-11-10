package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.login

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.ResultUser
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class LoginViewState(
    val isLoading: Boolean = false,
    val data: ResultUser? = null,
) : IViewState