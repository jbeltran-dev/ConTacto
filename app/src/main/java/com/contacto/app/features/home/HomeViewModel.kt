package com.contacto.app.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.contacto.app.core.call.PhoneCaller
import com.contacto.app.core.data.Contact
import com.contacto.app.core.data.repository.ContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ContactRepository,
    private val phoneCaller: PhoneCaller
) : ViewModel() {

    // Stream of contacts read from Room in real time.
    val contacts: StateFlow<List<Contact>> = repository.contacts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        // Initial load in case there are any predefined contacts.
        viewModelScope.launch {
            repository.seedInitialContactsIfEmpty()
        }
    }

    /**
     * Make a call to the selected contact
     */
    fun onCallContact(contact: Contact) {
        phoneCaller.makeCall(contact.phoneNumber)
    }

    /**
     * Hear contact name (TTS)
     */
    fun onListenContact(contact: Contact) {
        // Text-to-Speech Logic
    }

    /**
     * Save a new contact to the Room database.
     */
    fun addContact(name: String, phoneNumber: String, hexadecimalColor: String, initials: String) {
        viewModelScope.launch {
            val newContact = Contact(
                id = 0,
                name = name,
                phoneNumber = phoneNumber,
                hexadecimalColor = hexadecimalColor,
                isFavorite = false,
                initials = initials
            )
            repository.addContact(newContact)
        }
    }

    companion object {
        fun provideFactory(
            repository: ContactRepository,
            phoneCaller: PhoneCaller
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(repository, phoneCaller) as T
            }
        }
    }
}