package com.example.gooner10.androiddeveloperfundamentals.database.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Entity for Room
 */
@Entity(tableName = "room_word_list")
class RoomWordItem {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "word_column")
    var word: String? = ""
}