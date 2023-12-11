package com.example.cine

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class FilmListActivity : AppCompatActivity() {
    companion object {
        val EXTRA_FILM_TITLE = "EXTRA_FILM_TITLE"
        val EXTRA_DIRECTOR = "EXTRA_DIRECTOR"
        val EXTRA_POSTER_ID = "EXTRA_POSTER_ID"
        val EXTRA_GENDER = "EXTRA_GENDER"
        val EXTRA_YEAR = "EXTRA_YEAR"
        val EXTRA_FORMAT = "EXTRA_FORMAT"
        val EXTRA_LINK_IMDB = "EXTRA_LINK_IMDB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)
        val btnFilmA: Button = findViewById(R.id.btnFilmA)
        val btnFilmB: Button = findViewById(R.id.btnFilmB)
        val btnAbout: Button = findViewById(R.id.btnAbout)

        btnFilmA.setOnClickListener {
            navigateToDataFilm(
                getString(R.string.fight_club),
                getString(R.string.david_fincher),
                R.drawable.el_club_de_la_lucha,
                getString(R.string.drama),
                getString(R.string.year_1999),
                getString(R.string.panoramic),
                "https://www.imdb.com/title/tt0137523/"
            )
        }
        btnFilmB.setOnClickListener {
            navigateToDataFilm(
                getString(R.string.saving_private_ryan),
                getString(R.string.steven_spielberg),
                R.drawable.salvando_soldado_ryan,
                getString(R.string.war),
                getString(R.string.year_1998),
                getString(R.string.anamorphic_35_mm),
                "https://www.imdb.com/title/tt0120815/"
            )
        }
        btnAbout.setOnClickListener { navigateToAbout() }

    }

    private fun navigateToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun navigateToDataFilm(
        film: String,
        director: String,
        posterID: Int,
        gender: String,
        year: String,
        format: String,
        linkIMDB: String
    ) {
        val intent = Intent(this, FilmDataActivity::class.java)
        intent.putExtra(FilmListActivity.EXTRA_FILM_TITLE, film)
        intent.putExtra(FilmListActivity.EXTRA_DIRECTOR, director)
        intent.putExtra(FilmListActivity.EXTRA_POSTER_ID, posterID)
        intent.putExtra(FilmListActivity.EXTRA_GENDER, gender)
        intent.putExtra(FilmListActivity.EXTRA_YEAR, year)
        intent.putExtra(FilmListActivity.EXTRA_FORMAT, format)
        intent.putExtra(FilmListActivity.EXTRA_LINK_IMDB, linkIMDB)
        startActivity(intent)
    }



}