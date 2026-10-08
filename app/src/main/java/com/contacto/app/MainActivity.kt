package com.contacto.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.contacto.app.core.call.PhoneCaller
import com.contacto.app.core.data.local.AppDatabase
import com.contacto.app.core.data.repository.ContactRepository
import com.contacto.app.core.permissions.PermissionManager
import com.contacto.app.features.home.HomeScreen
import com.contacto.app.features.home.HomeViewModel

/**
 * Main entry point of the ConTacto application. Connects the UI, ViewModel, phone calling
 * functionality, and call permission handling.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialization of the Room Database and its Repository
        val database = AppDatabase.getInstance(this)
        val repository = ContactRepository(database.contactDao())

        // Call service initialization
        val phoneCaller = PhoneCaller(this)

        setContent {
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(repository, phoneCaller)
            )

            val contacts by viewModel.contacts.collectAsState()

            // Managing call permissions
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