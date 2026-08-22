package com.contacto.app.features.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Composable that displays the application's bottom navigation bar.
 *
 * This bar provides quick access to the main sections of the application.
 * Currently, it uses placeholders for icons and labels that should be updated
 * as the navigation logic is implemented.
 */
@Composable
fun BottomBar() {
    // Material 3 NavigationBar with a white background and slight elevation
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {

        // Placeholder item 1
        NavigationBarItem(
            enabled = false,
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Test"
                )
            },
            label = {
                Text("Con")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7C3AED),
                selectedTextColor = Color(0xFF7C3AED),
                indicatorColor = Color(0xFFEDE9FE),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // Placeholder item 2 (Selected by default)
        NavigationBarItem(
            enabled = false,
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Test",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = {
                Text("Tac")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7C3AED),
                selectedTextColor = Color(0xFF7C3AED),
                indicatorColor = Color(0xFFEDE9FE),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // Placeholder item 3
        NavigationBarItem(
            enabled = false,
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Test"
                )
            },
            label = {
                Text("To")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7C3AED),
                selectedTextColor = Color(0xFF7C3AED),
                indicatorColor = Color(0xFFEDE9FE),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}