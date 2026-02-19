package com.example.davomatdaftarchasi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "davomat")
data class Davomat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val talabaId: Int,
    val sana: String,
    val holat: String
)