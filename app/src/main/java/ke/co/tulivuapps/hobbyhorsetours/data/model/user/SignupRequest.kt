package ke.co.tulivuapps.hobbyhorsetours.data.model.user

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignupRequest(
    val name:String,
    val email : String,
    val password: String,
    val provider: String = "mobile",
    val img: String = "mobile"
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): SignupRequest? {
            return try {
                Gson().fromJson(jsonString, SignupRequest::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}