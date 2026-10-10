package com.contacto.app.features.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.contacto.app.R

/**
 * Composable that displays the empty state with proportional vertical spacing,
 * the paper airplane illustration, descriptive text, and the outlined pill button.
 *
 * @param onAddClick Callback invoked when the "Add my first contact" button is pressed.
 * @param modifier Optional modifier to customize the layout.
 */
@Composable
fun EmptyContactsState(
    onAddClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Top spacer to move the illustration away from the header (top red box)
        Spacer(modifier = Modifier.height(32.dp))

        // Vector illustration of a paper airplane
        Image(
            painter = painterResource(id = R.drawable.ic_empty_contacts),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Main title
        Text(
            text = "Conectemos\ncon los tuyos",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1E1B4B),
            textAlign = TextAlign.Center,
            lineHeight = 31.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Subtítulo descriptivo
        Text(
            text = "Agrega a tus familiares y amigos\npara comunicarte fácilmente con ellos.",
            fontSize = 15.sp,
            color = Color(0xFF71717A),
            textAlign = TextAlign.Center,
            lineHeight = 21.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Pill-style outlined button
        OutlinedButton(
            onClick = onAddClick,
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.5.dp, Color(0xFF8B5CF6).copy(alpha = 0.35f)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF8B5CF6).copy(alpha = 0.35f)
            ),
            enabled = false,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                tint = Color(0xFF8B5CF6).copy(alpha = 0.35f),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Agregar mi primer contacto",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8B5CF6).copy(alpha = 0.35f)
            )
        }

        // Bottom spacer to balance the distance to the BottomBar (bottom red box)
        Spacer(modifier = Modifier.height(32.dp))
    }
}