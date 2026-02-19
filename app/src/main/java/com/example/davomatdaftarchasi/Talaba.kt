package com.example.davomatdaftarchasi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "talabalar")
data class Talaba(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ism: String,
    val familiya: String,
    val sinf: String,
    val guruh: String
)