package com.example.davomatdaftarchasi

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.davomatdaftarchasi.Talaba

@Dao
interface TalabaDao {

    @Insert
    suspend fun qoshish(talaba: Talaba)

    @Update
    suspend fun tahrirlash(talaba: Talaba)

    @Delete
    suspend fun ochirish(talaba: Talaba)

    @Query("SELECT * FROM talabalar ORDER BY familiya ASC, ism ASC")
    suspend fun barchasiniOlish(): List<Talaba>

    @Query("SELECT * FROM talabalar WHERE ism LIKE '%' || :qidiruv || '%' OR familiya LIKE '%' || :qidiruv || '%' ORDER BY familiya ASC")
    suspend fun qidirish(qidiruv: String): List<Talaba>

    @Query("SELECT * FROM talabalar WHERE sinf = :sinf ORDER BY familiya ASC")
    suspend fun sinfBoyichaFilter(sinf: String): List<Talaba>

    @Query("SELECT DISTINCT sinf FROM talabalar ORDER BY sinf ASC")
    suspend fun barchaSinflar(): List<String>

    @Query("SELECT * FROM talabalar WHERE id = :id")
    suspend fun idBoyichaOlish(id: Int): Talaba?
}