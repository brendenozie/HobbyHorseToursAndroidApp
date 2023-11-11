package ke.co.tulivuapps.hobbyhorsetours.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 27.03.2023
 */
@Parcelize
data class img(
    val id: String ="",
    @SerializedName(value = "publicId")
    val publicId: String,
    @SerializedName(value = "url")
    val url: String
): Parcelable