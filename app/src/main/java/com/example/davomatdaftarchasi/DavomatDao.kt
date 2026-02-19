package com.example.davomatdaftarchasi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.davomatdaftarchasi.Davomat

@Dao
interface DavomatDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saqlash(davomat: Davomat)

    @Query("SELECT * FROM davomat WHERE talabaId = :talabaId ORDER BY sana DESC")
    suspend fun talabaningDavomati(talabaId: Int): List<Davomat>

    @Query("SELECT * FROM davomat WHERE sana = :sana")
    suspend fun sanaBoyichaOlish(sana: String): List<Davomat>

    @Query("SELECT * FROM davomat WHERE talabaId = :talabaId AND sana = :sana LIMIT 1")
    suspend fun bugungiDavomat(talabaId: Int, sana: String): Davomat?

    @Query("SELECT * FROM davomat WHERE sana BETWEEN :boshlanish AND :tugash ORDER BY sana ASC")
    suspend fun sanaoraliqDavomat(boshlanish: String, tugash: String): List<Davomat>

    @Query("""
        SELECT COUNT(*) FROM davomat 
        WHERE talabaId = :talabaId 
        AND holat = 'kelmagan' 
        AND sana >= date('now', '-30 days')
    """)
    suspend fun kelmaganKunlar(talabaId: Int): Int

    @Query("SELECT DISTINCT sana FROM davomat ORDER BY sana DESC")
    suspend fun barchaSanalar(): List<String>


    @Query("SELECT holat FROM davomat WHERE talabaId = :id ORDER BY sana DESC LIMIT 1")
    suspend fun oxirgiHolat(id: Int): String?
    @Query("SELECT holat FROM davomat WHERE talabaId = :talabaId AND sana = :sana LIMIT 1")
    suspend fun holatBySana(talabaId: Int, sana: String): String?

}