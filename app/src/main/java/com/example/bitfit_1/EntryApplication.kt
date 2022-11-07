package com.example.bitfit_1

import android.app.Application

class EntryApplication: Application(){
    val db by lazy {AppDatabase.getInstance(this)}
}