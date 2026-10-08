package com.contacto.app.core.data.repository

import com.contacto.app.core.data.Contact
import com.contacto.app.core.data.local.ContactDao
import com.contacto.app.core.data.local.toDomain
import com.contacto.app.core.data.local.toEntity
import com.contacto.app.core.data.sampleContacts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository responsible for managing contact data operations using Room.
 */
class ContactRepository (private val contactDao: ContactDao ) {
    /**
     * Observable flow of contacts retrieved from Room and converted to domain models.
     */
    val contacts: Flow<List<Contact>> = contactDao.getAllContacts().map { entities ->
        entities.map { it.toDomain() }
    }

    /**
     * Seeds initial sample contacts into the database if it is currently empty.
     */
    suspend fun seedInitialContactsIfEmpty() {
        if (contactDao.getContactCount() == 0 && sampleContacts.isNotEmpty()) {
            contactDao.insertContacts(sampleContacts.map { it.toEntity() })
        }
    }

    /**
     * Inserts a new contact into the database.
     */
    suspend fun addContact(contact: Contact) {
        contactDao.insertContact(contact.toEntity())
    }
}