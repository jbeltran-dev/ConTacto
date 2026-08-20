package com.example.con_tacto.core.data

data class Contact(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val colorHex: String,
    val isFavorite: Boolean = false,
    val initials: String
)