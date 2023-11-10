
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity

@Composable
fun ShowTimerDialog(onDismiss: () -> Unit) {

    val context = LocalContext.current

//    (context as? FragmentActivity)?.supportFragmentManager?.let { manager ->
//        val builder = MaterialTimePicker.Builder()
//            .build()
//        builder.addOnPositiveButtonClickListener {
//
//        }
//        builder.addOnDismissListener {
//            onDismiss.invoke()
//        }
//        builder.show(manager, "TimePicker")
//    }

 }



@ExperimentalMaterialApi
@Composable
@Preview
fun ShowTimerDialogPreview() {
    ShowTimerDialog{}
}
