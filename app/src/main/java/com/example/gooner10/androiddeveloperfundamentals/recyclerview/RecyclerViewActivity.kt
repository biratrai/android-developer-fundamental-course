package com.example.gooner10.androiddeveloperfundamentals.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.*

class RecyclerViewActivity : AppCompatActivity() {
    private var wordListAdapter: WordListAdapter? = null
    private val wordList = LinkedList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        // Put initial data into the word list.
        for (i in 0..19) {
            wordList.addLast("Word " + i)
        }

        // Create an wordListAdapter and supply the data to be displayed.
        wordListAdapter = WordListAdapter(this, wordList)
        // Connect the wordListAdapter with the recycler view.
        recyclerview!!.adapter = wordListAdapter
        // Give the recycler view a default layout manager.
        recyclerview!!.layoutManager = LinearLayoutManager(this)

        // Add a floating action click handler for creating new entries.
        fab.setOnClickListener {
            val wordListSize = wordList.size
            // Add a new word to the wordList.
            wordList.addLast("+ Word " + wordListSize)
            // Notify the wordListAdapter, that the data has changed.
            recyclerview!!.adapter.notifyItemInserted(wordListSize)
            // Scroll to the bottom.
            recyclerview!!.smoothScrollToPosition(wordListSize)
        }
    }
}
