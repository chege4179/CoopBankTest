package com.peterchege.coopbanktest.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peterchege.coopbanktest.core.api.responses.LoginResponse
import com.peterchege.coopbanktest.ui.screens.login.LoginScreen
import com.peterchege.coopbanktest.ui.screens.welcome.WelcomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.LOGIN_SCREEN,
    ) {
        composable(
            route = Screens.LOGIN_SCREEN,

            ) {
            LoginScreen(
                navigateToWelcomeScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(key = "user",value = it)
                    navController.navigate(Screens.WELCOME_SCREEN)
                }
            )


        }
        composable(
            route = Screens.WELCOME_SCREEN,

            ) {
            val user = navController.previousBackStackEntry?.savedStateHandle?.get<LoginResponse>(key ="user")

            WelcomeScreen(
                navigateToLoginScreen = {
                    navController.navigate(Screens.LOGIN_SCREEN)
                },
                loggedInUser = user
            )
        }
    }

}