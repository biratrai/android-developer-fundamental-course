package com.example.gooner10.androiddeveloperfundamentals.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Database OpenHelper
 */
class WordListOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private var writableDB: SQLiteDatabase? = null
    private var readableDB: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(WORD_LIST_TABLE_CREATE)
        fillDatabaseWithData(db)
    }

    /**
     * Adds the initial data set to the database.
     * According to the docs, onCreate for the open helper does not run on the UI thread.
     *
     * @param db Database to fill with data since the member variables are not initialized yet.
     */
    private fun fillDatabaseWithData(db: SQLiteDatabase) {

        val words = arrayOf("Android", "Adapter", "ListView", "AsyncTask", "Android Studio", "SQLiteDatabase", "SQLOpenHelper", "Data model", "ViewHolder", "Android Performance", "OnClickListener")

        // Create a container for the data.
        val values = ContentValues()

        for (i in words.indices) {
            // Put column/value pairs for current row into the container.
            values.put(KEY_WORD, words[i]) // put() overrides existing values.
            // Insert the row.
            db.insert(WORD_LIST_TABLE, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(WordListOpenHelper::class.java.name,
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data")
        db.execSQL("DROP TABLE IF EXISTS " + WORD_LIST_TABLE)
        onCreate(db)
    }

    fun query(): List<WordItem> {
        val query = "SELECT  * FROM " + WORD_LIST_TABLE +
                " ORDER BY " + KEY_WORD + " ASC "

        var cursor: Cursor? = null
        val wordList: MutableList<WordItem> = arrayListOf()

        try {
            if (readableDB == null) {
                readableDB = readableDatabase
            }
            cursor = readableDB!!.rawQuery(query, null)
            while (cursor!!.moveToNext()) {
                val wordItem = WordItem()
                wordItem.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                wordItem.word = cursor.getString(cursor.getColumnIndex(KEY_WORD))
                wordList.add(wordItem)
            }
        } catch (e: Exception) {
            Log.d(TAG, "QUERY EXCEPTION! " + e) // Just log the exception
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor!!.close()
            return wordList
        }
    }

    companion object {

        private val TAG = WordListOpenHelper::class.java.simpleName

        // Declaring all these as constants makes code a lot more readable and looking like SQL.

        // Version has to be 1 first time or app will crash.
        private val DATABASE_VERSION = 1
        private val WORD_LIST_TABLE = "word_entries"
        private val DATABASE_NAME = "wordlist"

        // Column names...
        val KEY_ID = "_id"
        val KEY_WORD = "word"

        // ... and a string array of columns.
        private val COLUMNS = arrayOf(KEY_ID, KEY_WORD)

        // Build the SQL query that creates the table.
        private val WORD_LIST_TABLE_CREATE = "CREATE TABLE " + WORD_LIST_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " + // will auto-increment if no value passed

                KEY_WORD + " TEXT );"
    }

    fun search(searchText: String): String {
        var searchString = searchText
        val columns = arrayOf(KEY_WORD)
        val where = KEY_WORD + " LIKE ?"
        searchString = "%$searchString%"
        val whereArgs = arrayOf(searchString)

        var cursor: Cursor? = null
        var search_result = ""
        try {
            if (readableDB == null) {
                readableDB = readableDatabase
                Log.d(TAG, "readableDB: " + readableDB)
            }
            cursor = readableDB!!.query(WORD_LIST_TABLE, columns, where, whereArgs, null, null, null)
            Log.d(TAG, "cursor: " + cursor)
            Log.d(TAG, "cursor.count: " + cursor.count)
            cursor!!.moveToFirst()
            if (cursor.count > 0) {
                var index: Int
                var result: String
                // Iterate over the cursor, while there are entries.
                do {
                    // Don't guess at the column index. Get the index for the named column.
                    index = cursor.getColumnIndex(WordListOpenHelper.KEY_WORD)
                    // Get the value from the column for the current cursor.
                    result = cursor.getString(index)
                    // Add result to what's already in the text view.
                    search_result += result + "\n"
                } while (cursor.moveToNext())
                cursor.close()
            } else {
                search_result = "No result."
            }
        } catch (e: Exception) {
            Log.e(TAG, "SEARCH EXCEPTION! " + e) // Just log the exception
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor!!.close()
        }

        return search_result
    }
}