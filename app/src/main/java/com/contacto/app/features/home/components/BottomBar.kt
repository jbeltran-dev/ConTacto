package com.contacto.app.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Displays the bottom navigation bar for the Home screen.
 *
 * The navigation bar contains three main sections:
 * - Add: Used to create or add a new contact.
 * - Home: Represents the current screen and is visually highlighted.
 * - History: Provides access to the user's contact history.
 *
 * The Home option is positioned above the navigation surface as a
 * floating circular button to make it visually prominent.
 */
@Composable
fun BottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        // Main navigation surface with rounded top corners and elevation.
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp),
            color = Color.White,
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp
            ),
            shadowElevation = 15.dp
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Add option occupies one third of the navigation bar.
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.alpha(0.35f)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AddCircle,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(2.dp)
                        )

                        Text(
                            text = "Agregar",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // Empty center space reserved for the floating Home button.
                Spacer(
                    modifier = Modifier.weight(1f)
                )

                // History option occupies one third of the navigation bar.
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.alpha(0.35f)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.History,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(2.dp)
                        )

                        Text(
                            text = "Historial",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // Floating Home option positioned above the navigation surface.
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Circular container used to highlight the current Home section.
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = Color(0xFF8748B2),
                shadowElevation = 8.dp
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // Label for the currently selected Home section.
            Text(
                text = "Inicio",
                color = Color(0xFF894AB1),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Represents an individual item within the bottom navigation bar.
 *
 * @param icon Icon displayed for the navigation item.
 * @param text Text label displayed below the icon.
 * @param selected Indicates whether this item is currently selected.
 * @param selectedColor Color used when the item is selected.
 * @param onClick Callback invoked when the user taps the navigation item.
 */
@Composable
private fun BottomItem(
    icon: ImageVector,
    text: String,
    selected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit
) {

    // Selected items use the provided accent color; unselected items use a neutral dark gray.
    val color = if (selected) {
        selectedColor
    } else {
        Color(0xFF555555)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Clickable icon button for the navigation item.
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = color,
                modifier = Modifier.size(25.dp)
            )
        }

        // Navigation item label.
        Text(
            text = text,
            color = color,
            fontSize = 12.sp
        )
    }
}