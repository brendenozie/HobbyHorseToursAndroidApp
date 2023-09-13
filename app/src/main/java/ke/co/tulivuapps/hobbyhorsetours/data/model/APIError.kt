package ke.co.tulivuapps.hobbyhorsetours.data.model

import androidx.compose.runtime.Stable

/**
 * Created by brendenozie on 12.03.2023
 */

@Stable
data class APIError(val code: Long, val message: String)
