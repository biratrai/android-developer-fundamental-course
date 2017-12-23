package com.example.gooner10.androiddeveloperfundamentals.database

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.gooner10.androiddeveloperfundamentals.R
import com.example.gooner10.androiddeveloperfundamentals.database.WordListOpenHelper.Companion.DATABASE_NAME
import com.example.gooner10.androiddeveloperfundamentals.database.room.RoomWordDatabase
import com.example.gooner10.androiddeveloperfundamentals.database.room.RoomWordItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity() {

    private val TAG = DatabaseActivity::class.java.simpleName
    private var db: WordListOpenHelper? = null
    private var roomWordDb: RoomWordDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        // Create sqliteDbHelper
        db = WordListOpenHelper(this)

        // Create RoomDb
        roomWordDb = Room.databaseBuilder(applicationContext, RoomWordDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        insertInRoom()
        populateFromDb()
    }

    private fun insertInRoom() {
        putData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    private fun putData(): Observable<Array<String>> {
        return Observable.create { subscriber ->

            val words = arrayOf("Android", "Adapter", "ListView", "AsyncTask", "Android Studio",
                    "SQLiteDatabase", "SQLOpenHelper", "Data model", "ViewHolder",
                    "Android Performance", "OnClickListener")
            // api call...
            val wordList = arrayListOf<RoomWordItem>()
            for (i in words.indices) {
                val roomWordItem = RoomWordItem()
                roomWordItem.word = words[i]
                wordList.add(roomWordItem)
            }
            roomWordDb?.wordDao()?.insertAll(wordList)
            subscriber.onNext(words)
            subscriber.onComplete()
        }
    }

    private fun populateFromDb() {
        val wordItem: List<WordItem> = db!!.query()
        var wordList = ""
        for (word in wordItem) {
            Log.d(TAG, "" + word.word)
            wordList += word.word
            wordList += "\n"
        }
        wordListText.text = wordList
    }

    fun searchWord(view: View) {
        val searchText = searchEditText.text
        Log.d(TAG, "searchWord: " + searchText)
        if (searchText.isEmpty()) {
            Toast.makeText(this, "Search is empty", Toast.LENGTH_SHORT).show()
        } else {
            val searchString = db?.search(searchText.toString())
            wordListText.text = searchString.toString()
        }
    }

    fun searchRoom(view: View) {
        val searchText = searchEditText.text
        Log.d(TAG, "searchWord: " + searchText)
        if (searchText.isEmpty()) {
            Toast.makeText(this, "Search is empty", Toast.LENGTH_SHORT).show()
        } else {
            searchWordInRoom(searchText.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { resultSearch ->
                                wordListText.text = resultSearch.toString()
                            },
                            { error ->

                                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                            })
        }
    }

    private fun searchWordInRoom(searchText: String): Observable<String> {
        return Observable.create { subscriber ->
            val searchString = roomWordDb?.wordDao()?.findByWord(searchText)
            subscriber.onNext(searchString?.word.toString())
            subscriber.onComplete()
        }
    }
}
