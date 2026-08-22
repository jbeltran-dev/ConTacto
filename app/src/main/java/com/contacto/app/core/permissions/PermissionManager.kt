package com.contacto.app.core.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Provides utilities for checking permissions required by ConTacto,
 * such as the permission needed to make phone calls.
 */
object PermissionManager {

    /** Permission required to make phone calls. */
    const val CALL_PHONE_PERMISSION = Manifest.permission.CALL_PHONE

    /** Returns true if the call permission has been granted. */
    fun hasCallPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            CALL_PHONE_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }
}