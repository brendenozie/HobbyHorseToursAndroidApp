package ke.co.tulivuapps.hobbyhorsetours.data.model.user

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultUser(
    val id: String?,
    val name : String,
    val email : String,
    val image: String
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): ResultUser? {
            return try {
                Gson().fromJson(jsonString, ResultUser::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}