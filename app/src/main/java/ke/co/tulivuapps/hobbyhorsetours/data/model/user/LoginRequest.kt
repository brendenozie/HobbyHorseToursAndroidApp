package ke.co.tulivuapps.hobbyhorsetours.data.model.user

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    val email : String,
    val password: String
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): LoginRequest? {
            return try {
                Gson().fromJson(jsonString, LoginRequest::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}