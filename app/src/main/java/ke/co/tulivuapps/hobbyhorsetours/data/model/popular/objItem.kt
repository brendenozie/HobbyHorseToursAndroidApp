package ke.co.tulivuapps.hobbyhorsetours.data.model.popular

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class objItem(
    @SerializedName(value = "\$oid")
    val  oid :String
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): objItem? {
            return try {
                Gson().fromJson(jsonString, objItem::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}