package com.example.bitfit_1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


class AddActivity : AppCompatActivity() {
    private val entries= mutableListOf<Entry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_page)


        val entryAdapter=EntryAdapter(this, entries)


        val submitButton=findViewById<Button>(R.id.addButton)

        submitButton.setOnClickListener{

            val title=findViewById<EditText>(R.id.editTextTextPersonName)
            val description=findViewById<EditText>(R.id.editTextTextMultiLine)
            val date=findViewById<EditText>(R.id.editTextDate)
            val end=10000000
            val start=1
            val id=(Math.random() * (end - start + 1)).toInt() + start

            val entry= listOf(EntryEntity(id.toLong(),title.text.toString(),description.text.toString(),date.text.toString()))
            lifecycleScope.launch(Dispatchers.IO){
                (application as EntryApplication).db.entryDao().insertAll(entry)
            }



            Log.v("Check", entries.toString())
            entryAdapter.notifyDataSetChanged()
            title.setText("")
            description.setText("")
            date.setText("")

            this.finish()

        }
    }
}