package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 11.03.2023
 */

@Parcelize
data class OriginResponse(
    val name: String?,
    val url: String?,
) : Parcelable