package com.example.con_tacto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.con_tacto.core.call.PhoneCaller
import com.example.con_tacto.core.permissions.PermissionManager
import com.example.con_tacto.features.home.HomeScreen
import com.example.con_tacto.features.home.HomeViewModel

/**
 * Main entry point of the ConTacto application. Connects the UI, ViewModel, phone calling
 * functionality, and call permission handling.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the phone caller service outside setContent to avoid re-instantiation
        val phoneCaller = PhoneCaller(this)

        setContent {
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(phoneCaller)
            )

            val contacts by viewModel.contacts.collectAsState()

            // Handles the request for the call permission.
            val callPermissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (!isGranted) {
                    Toast.makeText(
                        this,
                        "Permiso necesario para llamar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            HomeScreen(
                contacts = contacts,
                onListen = { contact -> viewModel.onListenContact(contact) },
                onCall = { contact ->
                    if (PermissionManager.hasCallPermission(this)) {
                        viewModel.onCallContact(contact)
                    } else {
                        callPermissionLauncher.launch(PermissionManager.CALL_PHONE_PERMISSION)
                    }
                }
            )
        }
    }
}