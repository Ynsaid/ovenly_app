package com.example.ovenlyapp

import ConfirmNumberScreen
import DetailsScreen
import MainScreen
import SettingsScreen
import SignUpScreen
import SplashScreen
import StartScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ovenlyapp.ui.theme.Bg_color_Second
import com.example.ovenlyapp.ui.theme.OvenlyAppTheme
import com.example.ovenlyapp.ui.theme.White

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            OvenlyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()

                ) {
                    NavHost(navController = navController, startDestination = Route.Splash){
                        composable(Route.Splash){ SplashScreen(navController) }
                        composable(Route.Start){ StartScreen(navController) }
                        composable(Route.Signup){ SignUpScreen(navController) }
                        composable(Route.ConfirmNumber){ ConfirmNumberScreen(navController) }

                        // should when access to main screen that have the navbar can i go to every page i put it in
                        composable(Route.Main){ MainScreen() }
                        composable(Route.Details){ DetailsScreen() }
                        composable(Route.Settings){ SettingsScreen() }

                    }

                }
            }
        }
    }
}

