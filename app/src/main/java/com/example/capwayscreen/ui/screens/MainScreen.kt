package com.example.capwayscreen.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.capwayscreen.BottomNavGraph
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.capwayscreen.BottomBarScreen
import com.example.capwayscreen.R

//This class is the launch point for content navigation.
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold(
        topBar = { MainTopAppBar() },
        bottomBar = { BottomBar(navHostcontroller = navHostController) }
    ) {
        BottomNavGraph(navController = navHostController)
    }
}

@Composable
fun MainTopAppBar() {
    TopAppBar(
        title = {
            Text(
                "Account",
                color = Color.Black
            )
        },
        backgroundColor = Color.White,
        actions = {
            Image(
                painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .padding(15.dp)
                    .clip(shape = CircleShape)
            )
        }
    )
}

@Composable
fun BottomBar(navHostcontroller: NavHostController) {
    val screens = listOf(
        BottomBarScreen.AccountScreen,
        BottomBarScreen.SendScreen,
        BottomBarScreen.MoreScreen,
        BottomBarScreen.ContentScreen,
        BottomBarScreen.PhundsScreen
    )
    val navBackStackEntry by navHostcontroller.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //This predefined function is where we can add the navigation destinations.
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier
            .height(70.dp)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostcontroller = navHostcontroller
            )
        }
    }
}

//Extension function which is designed to facilitate adding items to the bottom nav menu.
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostcontroller: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    var yOffset = 0.dp
    var tint = Color.Unspecified
    var labelColor = colorResource(id = R.color.light_blue_capway)//R.color.light_blue_capway

    if (screen.title.isEmpty()) {
        yOffset = 20.dp
    } else {
        if (!selected) {
            tint = Color.LightGray
            labelColor = Color.LightGray
        }
    }

    BottomNavigationItem(
        label = {
            Text(text = screen.title, color = labelColor)
        },
        icon = {
            Icon(

                painter = painterResource(id = screen.icon),
                contentDescription = "Nav icon",
                tint = tint

            )
        },
        selected = selected,
        onClick = {
            navHostcontroller.navigate(screen.route) {
                popUpTo(navHostcontroller.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier
            .offset(0.dp, yOffset),
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
    )
}

