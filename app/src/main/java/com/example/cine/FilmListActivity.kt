package com.example.cine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)
        val btnFilmA: Button =findViewById(R.id.btnFilmA)
        val btnFilmB: Button=findViewById(R.id.btnFilmB)
        val btnAbout:Button=findViewById(R.id.btnAbout)
        btnFilmA.setOnClickListener { navigateToData() }
        btnFilmB.setOnClickListener { navigateToData() }
        btnAbout.setOnClickListener { navigateToAbout() }

    }

    private fun navigateToAbout() {
        val intent=Intent(this,AboutActivity::class.java)
        startActivity(intent )
    }

    private fun navigateToData() {
        val intent= Intent(this,FilmDataActivity::class.java)
        startActivity(intent)
    }
}