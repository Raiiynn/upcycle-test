package com.example.upcycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.upcycle.screens.GudangScreen
import com.example.upcycle.screens.HomeScreen
import com.example.upcycle.screens.ProjectScreen
import com.example.upcycle.screens.ScanScreen

import com.example.upcycle.ui.theme.UpCycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpCycleTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val sageGreen = Color(0xFF8FBC8F)
    val darkGreen = Color(0xFF2E8B57)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Gudang,
                    BottomNavItem.Project
                )

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = darkGreen,
                            indicatorColor = sageGreen.copy(alpha = 0.2f)
                        ),
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("scan") },
                containerColor = Color(0xFFFF8C00),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Scan Barang")
            }
        },
        floatingActionButtonPosition = FabPosition.End

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Gudang.route) { GudangScreen() }
            composable(BottomNavItem.Project.route) { ProjectScreen() }
            composable("scan") { ScanScreen() }
        }
    }
}
