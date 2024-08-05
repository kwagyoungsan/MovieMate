package com.example.moviemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviemate.ui.theme.MovieMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
        val apiKey = BuildConfig.API_KEY
        val test = BASE_URL+"${apiKey}&targetDt=20240804"
        /**
         * TODO
         * 1. Retrofit2 사용을 위한 MVVM 구조 서치
         * 2. 응답 받기
         * 3. Compose 공부
         */
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
    MovieMateTheme {
        Greeting("Android")
    }
}

/**
 * TODO
 * api키를 사용하는 방법
 * val apiKey = BuildConfig.API_KEY
 *
 */