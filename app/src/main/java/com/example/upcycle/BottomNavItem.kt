package com.example.upcycle

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Gudang : BottomNavItem("gudang", Icons.Default.Inventory2, "Gudang")
    object Project : BottomNavItem("project", Icons.Default.Build, "Proyek")
}