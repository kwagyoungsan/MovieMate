package com.example.moviemate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviemate.model.API.BASE_URL_KEY
import com.example.moviemate.ui.theme.MovieMateTheme
import com.example.moviemate.viewmodel.DailyViewModel

class MainActivity : ComponentActivity() {
    private val dailyViewModel: DailyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieMateTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }

//        dailyViewModel.getData()

        dailyViewModel.result.observe(this, Observer { data ->
            // 데이터를 받아서 필요한 작업 수행
            Log.d("MainActivity", "Observed data: $data")
        })
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