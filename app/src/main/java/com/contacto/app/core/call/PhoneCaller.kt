package com.contacto.app.core.call

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri

class PhoneCaller(private val context: Context) {
    /**
     * Initiates a direct call to the specified number.
     * Requires the Manifest.permission.CALL_PHONE permission.
     */
    fun makeCall(phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = "tel:$phoneNumber".toUri()
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(intent)
        } catch (e: SecurityException){
            Toast.makeText(context, "Error: Permiso de llamada no concedido", Toast.LENGTH_LONG).show()
        }
        catch (e: Exception){
            Toast.makeText(context, "No se pudo realizar la llamada: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}