package ke.co.tulivuapps.hobbyhorsetours.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 27.03.2023
 */
@Parcelize
data class img(
    val id: String,
    val publicId: String,
    val url: String
): Parcelable