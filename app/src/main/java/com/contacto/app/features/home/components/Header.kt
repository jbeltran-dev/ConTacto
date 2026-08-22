package com.contacto.app.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight

/**
 * Custom shape used to create the curved bottom edge of the home screen header.
 *
 * The shape creates inward curves on both bottom corners while keeping
 * the top edge straight.
 */
val CurvedHeaderShape = GenericShape { size, _ ->
    val radiusX = size.width * 0.12f
    val radiusY = size.height * 0.12f

    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height - radiusY)

    // Curved bottom-right corner
    quadraticTo(
        size.width,
        size.height,
        size.width - radiusX,
        size.height
    )

    lineTo(radiusX, size.height)

    // Curved bottom-left corner
    quadraticTo(
        0f,
        size.height,
        0f,
        size.height - radiusY
    )

    close()
}

/**
 * Composable that displays the main header of the ConTacto home screen.
 *
 * The header contains the application icon, title, and visual instructions
 * explaining the single-tap and double-tap interactions available on contact cards.
 *
 * The header uses a vertical gradient background and a custom curved shape
 * to define its visual appearance.
 *
 * @param modifier Modifier used to customize the layout and appearance of the header.
 */
@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(CurvedHeaderShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6542D8),
                        Color(0xFF8B4FD0),
                        Color(0xFFC35FA4)
                    )
                )
            )
    ) {

        // Decorative elements
        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.35f),
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 32.dp, top = 50.dp)
        )

        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.35f),
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 24.dp, top = 10.dp)
        )

        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.35f),
            fontSize = 22.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 32.dp, top = 60.dp)
        )

        // Main header content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 28.dp)
        ) {

            // Application icon
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(44.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Application title
            Text(
                text = "ConTacto",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Single-tap instruction
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.TouchApp,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Toca una vez para escuchar",
                    color = Color.White.copy(alpha = 0.95f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Double-tap instruction
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Dos veces para llamar",
                    color = Color.White.copy(alpha = 0.95f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}