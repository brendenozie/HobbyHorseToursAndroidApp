package ke.co.tulivuapps.hobbyhorsetours.data.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class UserResponse(
    val results: ResultUser
) : Parcelable