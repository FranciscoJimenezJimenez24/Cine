package com.example.cine

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class FilmListActivity : AppCompatActivity() {
    companion object{
        val EXTRA_FILM_TITLE = "EXTRA_FILM_TITLE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)
        val btnFilmA: Button =findViewById(R.id.btnFilmA)
        val btnFilmB: Button=findViewById(R.id.btnFilmB)
        val btnAbout:Button=findViewById(R.id.btnAbout)
        btnFilmA.setOnClickListener { navigateToDataFilm(getString(R.string.film_A)) }
        btnFilmB.setOnClickListener { navigateToDataFilm(getString(R.string.film_B)) }
        btnAbout.setOnClickListener { navigateToAbout() }

    }

    private fun navigateToAbout() {
        val intent=Intent(this,AboutActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    private fun navigateToDataFilm(film:String) {
        val intent= Intent(this,FilmDataActivity::class.java)
        intent.putExtra(FilmListActivity.EXTRA_FILM_TITLE, film)
        startActivity(intent)
    }



}