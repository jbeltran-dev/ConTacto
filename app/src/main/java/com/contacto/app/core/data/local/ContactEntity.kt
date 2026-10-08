package com.contacto.app.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.contacto.app.core.data.Contact

/**
 * Room entity representing the 'contacts' table in the local database.
 */
@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val hexadecimalColor: String,
    val isFavorite: Boolean = false,
    val initials: String
)

/**
 * Converts a Room [ContactEntity] into a domain [Contact] model.
 */
fun ContactEntity.toDomain(): Contact = Contact(
    id = id,
    name = name,
    phoneNumber = phoneNumber,
    hexadecimalColor = hexadecimalColor,
    isFavorite = isFavorite,
    initials = initials
)

/**
 * Converts a domain [Contact] model into a Room [ContactEntity].
 */
fun Contact.toEntity(): ContactEntity = ContactEntity(
    id = id,
    name = name,
    phoneNumber = phoneNumber,
    hexadecimalColor = hexadecimalColor,
    isFavorite = isFavorite,
    initials = initials
)