package com.example.davomatdaftarchasi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Talaba::class, Davomat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun talabaDao(): TalabaDao
    abstract fun davomatDao(): DavomatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "davomat_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
