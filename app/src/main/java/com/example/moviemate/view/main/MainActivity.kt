package com.example.moviemate.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviemate.R
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
            /**
             * Compose에서는 Fragment를 사용하지 않는다.
             * 기존에는 fragment와 navigation을 이용해서 파일 및 화면 이동을 했는데..
             * 이제는 어떻게 하나?
             * 그 방법을 찾아야 한다.
             *
             * bottomnavigation을 사용하면 기존에 fragment 처럼 사용할 수 있다고 하는데?
             * 더 찾아봐야 할 것 같다.
             * https://medium.com/geekculture/bottom-navigation-in-jetpack-compose-android-9cd232a8b16
             *
             */
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainFragment") {
        composable("mainFragment") { MainFragment() }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFragment() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Main Fragment") })
        },
        content = {
            Text("This is the Main Fragment", modifier = Modifier.padding(16.dp))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}