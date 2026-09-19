package com.contacto.app.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.contacto.app.R
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface

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
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

        // Decorative elements (Left)
        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.25f),
            fontSize = 35.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 36.dp, top = 10.dp)
        )

        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.35f),
            fontSize = 25.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 32.dp, bottom = 70.dp)
        )

        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.20f),
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 32.dp, bottom = 25.dp)
        )

        // Decorative elements (Right)
        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.25f),
            fontSize = 22.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 30.dp, top = 60.dp)
        )

        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.30f),
            fontSize = 28.sp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 32.dp, top = 30.dp)
        )

        Text(
            text = "✦",
            color = Color.White.copy(alpha = 0.15f),
            fontSize = 22.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 25.dp, bottom = 20.dp)
        )

        // Setting button
        Surface(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp, top = 5.dp)
                .size(45.dp),
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.65f),
            shadowElevation = 4.dp
        ) {
            IconButton(
                onClick = { },
                enabled = false
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = null,
                    tint = Color(0xFF894AB1).copy(alpha = 0.45f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Main header content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 0.dp)
        ) {

            // Application icon
            Image(
                painter = painterResource(id = R.drawable.ic_logo_iluminated),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(1.dp))

            // Application title
            Text(
                text = "ConTacto",
                color = Color.White,
                fontSize = 52.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(10.dp))

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

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Dos veces para llamar",
                    color = Color.White.copy(alpha = 0.95f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

            }
        }
    }
}