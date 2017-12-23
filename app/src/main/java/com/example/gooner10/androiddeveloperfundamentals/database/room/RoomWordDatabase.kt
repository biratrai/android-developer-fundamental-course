package com.example.gooner10.androiddeveloperfundamentals.database.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Room Database creation
 */
@Database(entities = [(RoomWordItem::class)], version = 2)
abstract class RoomWordDatabase : RoomDatabase() {
    abstract fun wordDao(): RoomWordDao
}