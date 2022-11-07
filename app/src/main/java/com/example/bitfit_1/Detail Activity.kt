package com.example.bitfit_1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        titleTextView=findViewById(R.id.title)
        dateTextView=findViewById(R.id.date)
        descriptionTextView=findViewById(R.id.description)

        // TODO: Get the extra from the Intent
        val entry=intent.getSerializableExtra(ENTRY_EXTRA) as Entry

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text=entry.title
        dateTextView.text=entry.date
        descriptionTextView.text=entry.description

    }
}