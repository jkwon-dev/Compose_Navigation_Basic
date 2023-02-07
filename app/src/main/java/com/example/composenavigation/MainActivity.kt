package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.theme.ComposenavigationTheme
object NavRoute{
    val SCREEN_A = "ScreenA"
    val SCREEN_B = "ScreenB"
    val SCREEN_C = "ScreenC"
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposenavigationTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyNavHost(navHostController = navController)
                }
            }
        }
    }
}

@Composable
fun MyNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoute.SCREEN_A
    ) {
        composable(NavRoute.SCREEN_A) {
            ScreenA{
                navHostController.navigate(NavRoute.SCREEN_B)
            }
        }
        composable(NavRoute.SCREEN_B) {
            ScreenB{
                navHostController.navigate(NavRoute.SCREEN_C)
            }
        }
        composable(NavRoute.SCREEN_C) {
            ScreenC{
                navHostController.navigate(NavRoute.SCREEN_A){
                    popUpTo(NavRoute.SCREEN_A){
                        inclusive=true
                    }
                }
            }
        }
    }
}
