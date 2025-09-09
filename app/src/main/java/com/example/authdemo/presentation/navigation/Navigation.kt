package com.example.authdemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authdemo.data.local.DataStoreManager
import com.example.authdemo.presentation.home.HomeScreen
import com.example.authdemo.presentation.login.LoginScreen
import com.example.authdemo.presentation.sign_up.SignUpScreen
import kotlinx.coroutines.flow.first

@Composable
fun Navigation(modifier: Modifier = Modifier,dataStoreManager: DataStoreManager) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf(Screen.SignUp) }

    LaunchedEffect(Unit) {
        val token = dataStoreManager.getUserToken().first()
        startDestination = if (token.isNullOrEmpty()){
            Screen.Login
        }else{
            Screen.Home
        }
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.SignUp) {
            SignUpScreen(navHostController = navController)
        }
        composable(Screen.Login) {
            LoginScreen(navHostController = navController)
        }

        composable(Screen.Home) {
            HomeScreen(navHostController = navController)
        }
    }
}
