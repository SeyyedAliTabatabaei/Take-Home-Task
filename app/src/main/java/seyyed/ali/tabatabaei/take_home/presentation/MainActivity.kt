package seyyed.ali.tabatabaei.take_home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import seyyed.ali.tabatabaei.take_home.presentation.home.HomeScreen
import seyyed.ali.tabatabaei.take_home.presentation.theme.TakeHomeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeHomeTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun GreetingPreview() {
    TakeHomeTheme {

    }
}