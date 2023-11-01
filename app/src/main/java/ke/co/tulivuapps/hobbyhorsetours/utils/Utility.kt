package ke.co.tulivuapps.hobbyhorsetours.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Patterns
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import ke.co.tulivuapps.hobbyhorsetours.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resumeWithException

/**
 * Created by brendenozie on 27.03.2023
 */

object Utility {

    inline fun <reified T> T.toJson(): String {
        return try {
            Gson().toJson(this)
        } catch (ex: Exception) {
            ""
        }
    }

    inline fun <reified T> String.fromJson(): T? {
        return try {
            Gson().fromJson(this, T::class.java)
        } catch (ex: Exception) {
            null
        }
    }

    fun Activity.launchActivity(
        packageName: String,
        className: String,
        flags: Int = -1,
        bundle: Bundle? = null
    ) {
        val intent = Intent(Intent.ACTION_VIEW).setClassName(packageName, className)
        if (flags != -1) {
            intent.flags = flags
        }
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    @Composable
    fun <T> rememberFlowWithLifecycle(
        flow: Flow<T>,
        lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED
    ): Flow<T> = remember(flow, lifecycle) {
        flow.flowWithLifecycle(
            lifecycle = lifecycle,
            minActiveState = minActiveState
        )
    }

    @Composable
    fun getAnimateFloat(): State<Float> {
        val infiniteTransition = rememberInfiniteTransition(label = "animateFloat")
        return infiniteTransition.animateFloat(
            initialValue = 24.0f,
            targetValue = 48.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 800,
                    delayMillis = 100,
                    easing = FastOutLinearInEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = "animateFloat"
        )
    }


    //Don't touch this. It's behind your reasoning!
    @OptIn(ExperimentalCoroutinesApi::class)
    @Suppress("UNCHECKED_CAST", "EXPERIMENTAL_API_USAGE")
    suspend fun <T> Task<T>.await(): T {
        if (isComplete) {
            val e = exception
            return if (e == null) {
                if (isCanceled) throw CancellationException("Task $this was cancelled normally.")
                else result as T
            } else {
                throw e
            }
        }

        return suspendCancellableCoroutine { cont ->
            addOnCompleteListener {
                val e = exception
                if (e == null) {
                    if (isCanceled) cont.cancel() else cont.resume(result as T) {}
                } else {
                    cont.resumeWithException(e)
                }
            }
        }
    }

    fun Context.validPasswordOrThrow(password: String) {
        if (password.length < 6) throw Exception(getString(R.string.password_should_be_at_least_6_characters))
    }

    fun Context.validEmailOrThrow(email: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            throw Exception(getString(R.string.not_a_valid_email))
    }

//region
//note: this validates Egyptian numbers only.
//fun Context.validPhoneNumberOrThrow(phoneNumber: String) {
//    //starts with '+20', followed by 1, followed by 0 for 010 or 1 for 011 or 2 for 012 or 5 for 015, then the remaining 8 digits.
//    if (!Pattern.matches(
//            "\\+20[1][0125]\\d{8}",
//            phoneNumber
//        )
//    ) throw Exception(getString(R.string.not_a_valid_number))
//}
//endregion

    private fun ConnectivityManager.isConnected(): Boolean {
        val capabilities = getNetworkCapabilities(activeNetwork) ?: return false
        val wifiConnected = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        val mobileDataActive = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        val ethernetConnected = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        return wifiConnected || mobileDataActive || ethernetConnected
    }

    fun Context.isConnected(): Boolean {
        return (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isConnected()
    }

    fun Context.connectedOrThrow() {
        if (!isConnected()) throw Exception(getString(R.string.you_are_offline))
    }

    fun Context.isSystemDarkMode(): Boolean {
        return (resources.configuration.uiMode + Configuration.UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES
    }
}