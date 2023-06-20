package com.dviss.shoptest.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dviss.shoptest.ui.navigation.Route
import com.dviss.shoptest.ui.navigation.routeList
import com.dviss.shoptest.ui.util.getNavigationIconByRoute
import com.dviss.shoptest.ui.util.getTopAppBarTitleByRoute

@Composable
fun MyBottomNavigation(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Route.MENU

    val context = LocalContext.current

    NavigationBar {
        routeList.forEachIndexed { _, route ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = getNavigationIconByRoute(route = route)),
                        contentDescription = null
                    )
                },
                label = { Text(text = getTopAppBarTitleByRoute(context, route = route)) },
                selected = currentRoute == route,
                onClick = {
                    navController.navigate(route) {
                        popUpTo(Route.MENU)
                    }
                }
            )
        }
    }
}