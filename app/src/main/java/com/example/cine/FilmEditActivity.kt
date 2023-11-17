package com.example.cine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)
        val btnSave:Button=findViewById(R.id.btnSave)
        val btnCancel:Button=findViewById(R.id.btnCancel)
        btnSave.setOnClickListener { finish() }
        btnCancel.setOnClickListener { finish() }

    }


}