package com.dviss.shoptest.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dviss.shoptest.ui.AppViewModel
import com.dviss.shoptest.ui.components.MyBottomNavigation
import com.dviss.shoptest.ui.screen.cart.CartScreen
import com.dviss.shoptest.ui.screen.menu.MenuScreen
import com.dviss.shoptest.ui.screen.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    //navigation
    val navController = rememberNavController()

    //viewmodel
    val viewModel: AppViewModel = viewModel()

    //snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { MyBottomNavigation(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Route.MENU,
            modifier = Modifier.padding(padding)
        ) {
            composable(Route.MENU) {
                MenuScreen(viewModel = viewModel)
            }
            composable(Route.PROFILE) {
                ProfileScreen(viewModel = viewModel)
            }
            composable(Route.CART) {
                CartScreen(viewModel = viewModel)
            }
        }
    }

}