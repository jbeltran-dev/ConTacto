package com.contacto.app.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.draw.scale

@Composable
fun BottomBar() {

    var isAddSelected by remember { mutableStateOf(false) }
    var isAddPressed by remember { mutableStateOf(false) }
    var isHomeSelected by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()
    var flashJob by remember { mutableStateOf<Job?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Main navigation bar
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

                // Add contact
                val itemColor = if (isAddSelected) {
                    Color(0xFF8748B2)
                } else {
                    Color(0xFF77717D)
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (isAddPressed) Color(0xFFF0EDF3)
                            else Color.Transparent
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                isAddSelected = true
                                isHomeSelected = false

                                flashJob?.cancel()
                                isAddPressed = true

                                flashJob = scope.launch {
                                    delay(200.milliseconds)
                                    isAddPressed = false
                                }
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.AddCircle,
                            contentDescription = "Agregar contacto",
                            modifier = Modifier.size(26.dp),
                            tint = itemColor
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Agregar",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = itemColor
                        )
                    }
                }

                // Space for floating Home button
                Spacer(modifier = Modifier.weight(1f))

                // History
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
                            contentDescription = "Historial",
                            modifier = Modifier.size(26.dp)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Historial",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // Floating Home button
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val homeInteractionSource = remember { MutableInteractionSource() }
            val isHomePressed by homeInteractionSource.collectIsPressedAsState()

            val homeScale by animateFloatAsState(
                targetValue = if (isHomePressed) 0.94f else 1f,
                animationSpec = spring(
                    dampingRatio = 0.6f,
                    stiffness = 500f
                ),
                label = "homeScale"
            )

            Surface(
                modifier = Modifier
                    .size(64.dp)
                    .scale(homeScale)
                    .clickable(
                        interactionSource = homeInteractionSource,
                        indication = null
                    ) {
                        isHomeSelected = true
                        isAddSelected = false
                    },
                shape = CircleShape,
                color = Color(0xFF8748B2),
                shadowElevation = 8.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Inicio",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Inicio",
                color = if (isHomeSelected) {
                    Color(0xFF894AB1)
                } else {
                    Color(0xFF77717D)
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
