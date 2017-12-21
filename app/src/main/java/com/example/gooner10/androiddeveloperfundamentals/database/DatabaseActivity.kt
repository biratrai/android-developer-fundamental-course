package com.example.gooner10.androiddeveloperfundamentals.database

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gooner10.androiddeveloperfundamentals.R

class DatabaseActivity : AppCompatActivity() {

    private var db: WordListOpenHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        db = WordListOpenHelper(this)
    }
}
