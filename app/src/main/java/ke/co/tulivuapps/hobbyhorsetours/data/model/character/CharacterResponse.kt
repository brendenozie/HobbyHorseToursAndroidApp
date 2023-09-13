package ke.co.tulivuapps.hobbyhorsetours.data.model.character

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.data.model.InfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.data.model.ResultCharacter
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class CharacterResponse(
    val info: InfoResponse,
    val results: List<ResultCharacter>
) : Parcelable