package ke.co.tulivuapps.hobbyhorsetours

import android.app.Application
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by brendenozie on 13.03.2023
 */

@Stable
@HiltAndroidApp
class HobbyHorseToursApp : Application() {

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}