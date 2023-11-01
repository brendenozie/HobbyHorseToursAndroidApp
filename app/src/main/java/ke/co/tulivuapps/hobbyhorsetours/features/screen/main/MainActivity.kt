package ke.co.tulivuapps.hobbyhorsetours.features.screen.main

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ke.co.tulivuapps.hobbyhorsetours.HobbyHorseToursApp
import ke.co.tulivuapps.hobbyhorsetours.features.navigation.NavGraph
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.HobbyHorseToursAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: HobbyHorseToursApp

    private val viewModel by viewModels<MainViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDark = isSystemInDarkTheme()

            HobbyHorseToursAppTheme(darkTheme = isDark) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val onBoarded = viewModel.onBoarded
                    Toast.makeText(this, "$onBoarded", Toast.LENGTH_SHORT).show()
                    NavGraph(true)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HobbyHorseToursAppTheme {
        NavGraph(false)
    }
}