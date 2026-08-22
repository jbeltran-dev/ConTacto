package com.contacto.app.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.contacto.app.core.data.Contact
import com.contacto.app.core.data.sampleContacts
import com.contacto.app.features.home.components.BottomBar
import com.contacto.app.features.home.components.ContactCard
import com.contacto.app.features.home.components.Header

/**
 * Composable that displays the home screen of the application.
 *
 * This screen shows a list of contacts with options to listen to their voice notes
 * or initiate a call. It includes a header and a bottom navigation bar.
 *
 * @param contacts The list of [Contact] objects to be displayed. Defaults to [sampleContacts].
 * @param onListen Callback function invoked when the user wants to listen to a contact's voice note.
 * @param onCall Callback function invoked when the user wants to call a contact.
 */
@Composable
fun HomeScreen(
    contacts: List<Contact> = sampleContacts,
    onListen: (Contact) -> Unit,
    onCall: (Contact) -> Unit
) {
    // Root layout using Scaffold to provide basic material design structure
    Scaffold(
        containerColor = Color(0xFFF5F6FA),
        bottomBar = {
            // Persistent bottom navigation bar
            BottomBar()
        }
    ) { padding ->

        // Efficiently display a scrollable list of contacts
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding.calculateBottomPadding())
                .background(Color(0xFFF7F7F9)),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Static header at the top of the list
            item {
                Header()
            }

            // Dynamic list of contact cards
            items(contacts) { contact ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    ContactCard(
                        contact = contact,
                        onListen = onListen,
                        onCall = onCall
                    )
                }
            }
        }
    }
}