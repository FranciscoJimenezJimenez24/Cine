package com.example.cine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmDataActivity : AppCompatActivity() {
    companion object{
        val EXTRA_FILM_A = R.string.film_A
        val EXTRA_FILM_B = R.string.film_B
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)
        val btnRelatedFilm: Button =findViewById(R.id.btnRelatedFilm)
        val btnEditFilm:Button=findViewById(R.id.btnEditFilm)
        val btnBackMain:Button=findViewById(R.id.btnBackMain)
        btnRelatedFilm.setOnClickListener { navigateToData()}
        btnEditFilm.setOnClickListener { navigateToEdit() }
        btnBackMain.setOnClickListener { navigateToList() }

    }

    private fun navigateToList() {
        val intent= Intent(this,FilmListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToEdit() {
        val intent= Intent(this,FilmEditActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToData() {
        val intent= Intent(this,FilmDataActivity::class.java)
        startActivity(intent)
    }



}