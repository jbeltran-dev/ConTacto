package com.example.con_tacto.core.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.doubleTapGesture(
    onSingleTap: () -> Unit,
    onDoubleTap: () -> Unit
): Modifier = this.pointerInput(Unit) {
    detectTapGestures(
        onTap = { onSingleTap() },
        onDoubleTap = { onDoubleTap() }
    )
}