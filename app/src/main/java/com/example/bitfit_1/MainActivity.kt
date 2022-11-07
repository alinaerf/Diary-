package com.example.bitfit_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit_1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val entries= mutableListOf<Entry>()
    private lateinit var entriesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        entriesRecyclerView=findViewById(R.id.entries)
        val entryAdapter=EntryAdapter(this, entries)
        entriesRecyclerView.adapter=entryAdapter
        val button=findViewById<Button>(R.id.button)

        lifecycleScope.launch {
            (application as EntryApplication).db.entryDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Entry(
                        entity.date,
                        entity.title,
                        entity.description
                    )
                }.also { mappedList ->
                    entries.clear()
                    entries.addAll(mappedList)
                    entryAdapter.notifyDataSetChanged()
                }
            }
        }

        entriesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            entriesRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        button.setOnClickListener{

            val intent= Intent(this, AddActivity::class.java)
            Log.v("BUTTON", "you pressed button")
            //Toast.makeText(this, "PRESSED BUTTON", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

    }


}