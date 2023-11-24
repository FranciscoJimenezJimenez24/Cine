package com.example.cine

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class FilmListActivity : AppCompatActivity() {
    companion object{
        val EXTRA_FILM_TITLE = "EXTRA_FILM_TITLE"
        val EXTRA_DIRECTOR="EXTRA_DIRECTOR"
        val EXTRA_POSTER="POSTER"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)
        val btnFilmA: Button =findViewById(R.id.btnFilmA)
        val btnFilmB: Button=findViewById(R.id.btnFilmB)
        val btnAbout:Button=findViewById(R.id.btnAbout)
        btnFilmA.setOnClickListener { navigateToDataFilm(getString(R.string.fight_club),getString(R.string.david_fincher),R.drawable.salvando_soldado_ryan) }
        btnFilmB.setOnClickListener { navigateToDataFilm(getString(R.string.saving_private_ryan),getString(R.string.steven_spielberg)) }
        btnAbout.setOnClickListener { navigateToAbout() }

    }

    private fun navigateToAbout() {
        val intent=Intent(this,AboutActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    private fun navigateToDataFilm(film:String, director:String,poster: ImageView) {
        val intent= Intent(this,FilmDataActivity::class.java)
        intent.putExtra(FilmListActivity.EXTRA_FILM_TITLE, film)
        intent.putExtra(FilmListActivity.EXTRA_DIRECTOR,director)
        intent.putExtra(FilmListActivity.EXTRA_POSTER,poster)
        startActivity(intent)
    }



}