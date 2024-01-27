package hu.varsanyi.punkapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import hu.varsanyi.punkapiapp.data.di.dataModule
import hu.varsanyi.punkapiapp.features.di.featuresModule
import hu.varsanyi.punkapiapp.ui.PunkApiAppNavigation
import hu.varsanyi.punkapiapp.ui.theme.PunkAPIAppTheme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(dataModule, featuresModule)
        }
        setContent {
            PunkAPIAppTheme {
                // A surface container using the 'background' color from the theme
                PunkApiAppNavigation(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PunkAPIAppTheme {
        Greeting("Android")
    }
}