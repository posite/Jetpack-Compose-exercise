package com.posite.compose1.presentation.layout.vm.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerItems(val title: String, val icon: ImageVector) {
    data object Profile : DrawerItems("내 정보", Icons.Default.Person)
    data object Location : DrawerItems("내 위치", Icons.Default.LocationOn)
    data object Settings : DrawerItems("설정", Icons.Default.Settings)
}