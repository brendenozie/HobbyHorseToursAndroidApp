package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class CharacterResponse(
    val info: InfoResponse,
    val results: List<Result>
) : Parcelable