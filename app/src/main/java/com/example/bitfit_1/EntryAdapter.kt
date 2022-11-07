package com.example.bitfit_1

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val ENTRY_EXTRA="ENTRY_EXTRA"

class EntryAdapter(private val context: Context, private val entries: List<Entry>):

    RecyclerView.Adapter<EntryAdapter.ViewHolder>(){


    inner class ViewHolder(entryView: View): RecyclerView.ViewHolder(entryView),
        View.OnClickListener{
        val titleTextView=entryView.findViewById<TextView>(R.id.title)
        val dateTextView=entryView.findViewById<TextView>(R.id.date)
        val descriptionTextView=entryView.findViewById<TextView>(R.id.description)

        init{
            entryView.setOnClickListener(this)
            //button.setOnClickListener(this)
        }


        override fun onClick(v:View?){
            val entry=entries[absoluteAdapterPosition]
            val intent= Intent(context, DetailActivity::class.java)

            intent.putExtra(ENTRY_EXTRA, entry)
            context.startActivity(intent)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryAdapter.ViewHolder {
        val context=parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_entry, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }
    override fun onBindViewHolder(viewHolder: EntryAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val entry: Entry = entries.get(position)
        viewHolder.titleTextView.text=entry.title
        viewHolder.dateTextView.text=entry.date
        viewHolder.descriptionTextView.text= entry.description
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}