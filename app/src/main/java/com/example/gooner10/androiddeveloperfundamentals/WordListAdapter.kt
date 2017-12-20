package com.example.gooner10.androiddeveloperfundamentals

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.wordlist_item.view.*
import java.util.*

/**
 * Adapter for {@link RecyclerViewActivity}
 */
class WordListAdapter(context: Context, private val wordList: LinkedList<String>) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * Inflates an item view and returns a new view holder that contains it.
     * Called when the RecyclerView needs a new view holder to represent an item.
     *
     * @param parent The view group that holds the item views.
     * @param viewType Used to distinguish views, if more than one type of item view is used.
     * @return a view holder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        // Inflate an item view.
        val itemView = inflater.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(itemView)
    }

    /**
     * Sets the contents of an item at a given position in the RecyclerView.
     * Called by RecyclerView to display the data at a specificed position.
     *
     * @param holder The view holder for that position in the RecyclerView.
     * @param position The position of the item in the RecycerView.
     */
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        // Retrieve the data for that position.
        val mCurrent = wordList[position]
        // Add the data to the view holder.
        holder.wordItemView.text = mCurrent
    }

    /**
     * Returns the size of the container that holds the data.
     *
     * @return Size of the list of data.
     */
    override fun getItemCount(): Int {
        return wordList.size
    }

    /**
     * Creates a new custom view holder to hold the view to display in the RecyclerView.
     *
     * @param itemView The view in which to display the data.
     */
    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val wordItemView = itemView.word

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // All we do here is prepend "Clicked! " to the text in the view, to verify that
            // the correct item was clicked. The underlying data does not change.
            wordItemView.text = "Clicked! " + wordItemView.text
        }
    }
}