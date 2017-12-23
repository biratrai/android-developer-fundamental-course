package com.example.gooner10.androiddeveloperfundamentals.database.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Dao for the Room
 */
@Dao
interface RoomWordDao {
    @get:Query("SELECT * FROM room_word_list")
    val all: List<RoomWordItem>

    @Query("SELECT * FROM room_word_list WHERE word_column LIKE :word")
    fun findByWord(word: String): RoomWordItem

    @Insert
    fun insertAll(words: ArrayList<RoomWordItem>)

    @Delete
    fun delete(user: RoomWordItem)
}
