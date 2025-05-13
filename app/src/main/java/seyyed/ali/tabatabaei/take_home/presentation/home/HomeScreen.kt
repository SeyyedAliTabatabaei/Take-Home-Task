package seyyed.ali.tabatabaei.take_home.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import seyyed.ali.tabatabaei.domain.model.enums.BulbStatus
import seyyed.ali.tabatabaei.take_home.R
import seyyed.ali.tabatabaei.take_home.presentation.theme.TakeHomeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val connectionStatus by viewModel.connectionStatus.collectAsState()
    val lightStatus by viewModel.lightBulbStatus.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = stringResource(R.string.connection_status , connectionStatus.name) ,
                    style = MaterialTheme.typography.bodyMedium
                ) }
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.SpaceAround
        ){
            LightBulb(
                modifier = Modifier.padding(horizontal = 60.dp),
                bulbStatus = lightStatus.lightBulbStatus
            )

            SetBrightnessLightBulb(
                modifier = Modifier.padding(vertical = 20.dp , horizontal = 20.dp)
            )

            ButtonLightBulb(
                modifier = Modifier.padding(vertical = 20.dp) ,
                bulbStatus = lightStatus.lightBulbStatus
            ){
                scope.launch {
                    viewModel.setLightStatus()
                }
            }
        }

    }

}

@Composable
fun LightBulb(modifier: Modifier = Modifier, bulbStatus: BulbStatus, brightness : Int = 50) {
    val brightnessLamp = when(bulbStatus){
        BulbStatus.ON -> brightness.toFloat()
        BulbStatus.OFF -> 0f
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.lamp_off) ,
            contentDescription = "Lamp Off",
            modifier = Modifier
        )
        Image(
            painter = painterResource(id = R.drawable.lamp_on) ,
            contentDescription = "Lamp On",
            modifier = Modifier.alpha(brightnessLamp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetBrightnessLightBulb(modifier: Modifier = Modifier) {
    Slider(
        value = 0f,
        onValueChange = {  },
        valueRange = 0f..100f,
        modifier = modifier ,
        thumb = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(5.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_lamp) ,
                    contentDescription = "Slider",
                    colorFilter = ColorFilter.tint(Color.White) ,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}

@Composable
fun ButtonLightBulb(modifier: Modifier = Modifier , bulbStatus: BulbStatus , clickButton : () -> Unit) {
    val isOn = bulbStatus == BulbStatus.ON

    Image(
        painter = painterResource(id = R.drawable.ic_power) ,
        contentDescription = "Switch",
        modifier = Modifier
            .size(80.dp)
            .shadow(15.dp, CircleShape)
            .background(Color.Gray, CircleShape)
            .padding(5.dp)
            .clickable { clickButton() },
        colorFilter = ColorFilter.tint(if (isOn) Color.Green else Color.White)
    )
}

@Preview(showBackground = true , showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    TakeHomeTheme {
        HomeScreen()
    }
    
}