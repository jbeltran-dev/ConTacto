package com.example.con_tacto.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.con_tacto.core.call.PhoneCaller
import com.example.con_tacto.core.data.Contact
import com.example.con_tacto.core.data.sampleContacts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val phoneCaller: PhoneCaller
) : ViewModel() {

    // Contact list status
    private val _contacts = MutableStateFlow(sampleContacts)
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    /**
     * Logic for making the call to the contact
     */
    fun onCallContact(contact: Contact) {
        phoneCaller.makeCall(contact.phoneNumber)
    }

    /**
     * Logic for listening for the name (pending implementation)
     */
    fun onListenContact(contact: Contact) {
        // The TTS (Text-to-Speech) logic will go here.
    }

    // Factory to inject the PhoneCaller into the ViewModel
    companion object {
        fun provideFactory(phoneCaller: PhoneCaller): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(phoneCaller) as T
            }
        }
    }
}