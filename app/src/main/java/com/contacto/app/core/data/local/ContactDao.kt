package com.contacto.app.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing contact operations in Room.
 */
@Dao
interface ContactDao {

    /**
     * Retrieves all contacts from the database ordered alphabetically by name.
     */
    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAllContacts(): Flow<List<ContactEntity>>

    /**
     * Returns the total count of contacts stored in the database.
     */
    @Query("SELECT COUNT(*) FROM contacts")
    suspend fun getContactCount(): Int

    /**
     * Inserts or replaces a single contact in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactEntity): Long

    /**
     * Inserts or replaces a list of contacts in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<ContactEntity>)

    /**
     * Deletes a specific contact from the database.
     */
    @Delete
    suspend fun deleteContact(contact: ContactEntity)
}
