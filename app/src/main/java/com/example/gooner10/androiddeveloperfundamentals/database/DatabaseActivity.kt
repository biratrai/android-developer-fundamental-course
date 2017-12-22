package com.example.gooner10.androiddeveloperfundamentals.database

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity() {

    private val TAG = DatabaseActivity::class.java.simpleName
    private var db: WordListOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        db = WordListOpenHelper(this)

        val wordItem: List<WordItem> = db!!.query()
        var wordList: String = ""
        for (word in wordItem) {
            Log.d(TAG, "" + word.word)
            wordList += word.word
            wordList += "\n"
        }
        wordListText.text = wordList
    }
}
