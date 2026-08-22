package com.contacto.app.features.home.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.contacto.app.core.data.Contact
import com.contacto.app.core.ui.doubleTapGesture
import androidx.core.graphics.toColorInt

/**
 * A specialized card that displays contact information with an accessibility-focused design.
 *
 * The card uses large text, high-contrast colors, and specific touch gestures to assist users
 * with visual or motor impairments. It features a colored accent bar, a large avatar with initials,
 * and the contact's name.
 *
 * Gestures:
 * - Single Tap: Triggers [onListen] to provide an auditory description of the contact.
 * - Double Tap: Triggers [onCall] to initiate a phone call.
 *
 * @param contact The [Contact] data to display.
 * @param onListen Callback for single-tap gesture (e.g., voice note playback).
 * @param onCall Callback for double-tap gesture (e.g., initiating a call).
 */
@Composable
fun ContactCard(
    contact: Contact,
    onListen: (Contact) -> Unit,
    onCall: (Contact) -> Unit
) {
    // Parse the contact's hex color into a Compose Color object
    val accentColor = Color(contact.colorHex.toColorInt())

    // Main Card container with custom elevation and shape
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .doubleTapGesture(
                onSingleTap = { onListen(contact) },
                onDoubleTap = { onCall(contact) }
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        // Card background with a linear gradient derived from the accent color
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colorStops = arrayOf(
                            0.00f to accentColor.lighten(0.70f),
                            0.55f to accentColor.lighten(0.45f),
                            0.82f to accentColor.lighten(0.15f),
                            1.00f to accentColor.darken(0.03f)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(1600f, 500f)
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Visual accent bar on the left edge
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp)
                    .clip(RoundedCornerShape(topStart = 32.dp, bottomStart = 32.dp))
                    .background(accentColor)
            )

            Spacer(modifier = Modifier.width(28.dp))

            // Large circular avatar showing contact initials
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .shadow(elevation = 4.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(accentColor)
                    .border(width = 5.dp, color = accentColor.lighten(0.75f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contact.initials,
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            // Column containing name and a visual "Contact" badge
            Column {
                Text(
                    text = contact.name,
                    color = Color(0xFF191919),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Small badge highlighting that this is a contact entry
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color.White.copy(alpha = 0.28f),
                                    Color.White.copy(alpha = 0.12f)
                                )
                            )
                        )
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.30f),
                            RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Contacto",
                        color = accentColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

/**
 * Returns a lighter version of this [Color].
 *
 * @param amount The percentage to lighten by, where 0.0 is no change and 1.0 is pure white.
 */
fun Color.lighten(amount: Float): Color = lerp(this, Color.White, amount)

/**
 * Returns a darker version of this [Color].
 *
 * @param amount The percentage to darken by, where 0.0 is no change and 1.0 is pure black.
 */
fun Color.darken(amount: Float): Color = lerp(this, Color.Black, amount)