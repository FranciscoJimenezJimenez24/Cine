package com.example.cine

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class FilmDataActivity : AppCompatActivity() {
    companion object {
        var EXTRA_FILM_TITLE = "EXTRA_FILM_TITLE"
        var EXTRA_DIRECTOR_FILM = "EXTRA_DIRECTOR_FILM"
        var EXTRA_POSTER_ID = "EXTRA_POSTER_ID"
        var EXTRA_GENDER = "EXTRA_GENDER"
        var EXTRA_FORMAT = "EXTRA_FORMAT"
        var EXTRA_YEAR_FILM = "EXTRA_YEAR_FILM"
        var EXTRA_LINK_IMDB = "EXTRA_LINK_IMDB"
        const val REQUEST_EDIT_FILM = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)

        val filmTitle = intent.getStringExtra(EXTRA_FILM_TITLE)
        val director = intent.getStringExtra(EXTRA_DIRECTOR_FILM)
        val poster_id = intent.getIntExtra(EXTRA_POSTER_ID, 0)
        val gender = intent.getStringExtra(EXTRA_GENDER)
        val format = intent.getStringExtra(EXTRA_FORMAT)
        val year = intent.getStringExtra(EXTRA_YEAR_FILM)
        val linkIMDB = intent.getStringExtra(EXTRA_LINK_IMDB)

        val tvFilm: TextView = findViewById(R.id.tvFilm)
        var tvDirector: TextView = findViewById(R.id.tvDirector)
        val ivPoster: ImageView = findViewById(R.id.ivPoster)
        val tvGender: TextView = findViewById(R.id.tvGender)
        val tvFormat: TextView = findViewById(R.id.tvFormat)
        val tvYear: TextView = findViewById(R.id.tvYear)
        val btnIMDB: Button = findViewById(R.id.btnIMDB)

        //val bitmap: String? = intent.getStringExtra("EXTRA_IMAGE")

        tvDirector.text = director
        tvFilm.text = filmTitle
        ivPoster.setImageResource(poster_id)
        tvGender.text = gender
        tvFormat.text = format
        tvYear.text = year


        val btnRelatedFilm: Button = findViewById(R.id.btnRelatedFilm)
        val btnEditFilm: Button = findViewById(R.id.btnEditFilm)
        val btnBackMain: Button = findViewById(R.id.btnBackMain)


        btnIMDB.setOnClickListener {
            if (linkIMDB != null) {
                openIMDbLink(linkIMDB)
            }
        }
        btnRelatedFilm.setOnClickListener {
            if (filmTitle != null) {
                navigateToData(filmTitle)
            }
        }
        btnEditFilm.setOnClickListener { navigateToEdit() }
        btnBackMain.setOnClickListener { navigateToList() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT_FILM) {
            // Verificar el resultado y actuar en consecuencia
            if (resultCode == RESULT_OK) {
                // El usuario guardó los cambios

                val tvDirector:TextView=findViewById(R.id.tvDirector)
                tvDirector.text = intent.getStringExtra(EXTRA_DIRECTOR_FILM)
                val tvFilm: TextView = findViewById(R.id.tvFilm)
                tvFilm.text = intent.getStringExtra(EXTRA_FILM_TITLE)
                //ivPoster.setImageResource(poster_id)
                val tvGender: TextView = findViewById(R.id.tvGender)
                tvGender.text = intent.getStringExtra(EXTRA_GENDER)
                val tvFormat: TextView = findViewById(R.id.tvFormat)
                tvFormat.text = intent.getStringExtra(EXTRA_FORMAT)
                val tvYear: TextView = findViewById(R.id.tvYear)
                tvYear.text = intent.getStringExtra(EXTRA_YEAR_FILM)

                val textViewChangesResult: TextView = findViewById(R.id.tvChange)
                textViewChangesResult.text = getString(R.string.saved_changes)
            } else if (resultCode == RESULT_CANCELED) {
                val textViewChangesResult: TextView = findViewById(R.id.tvChange)
                textViewChangesResult.text = getString(R.string.unsaved_changes)
            }
        }
    }

    private fun openIMDbLink(link: String) {
        val intent = Intent(ACTION_VIEW, Uri.parse(link))
        intent.setPackage("com.android.chrome")
        startActivity(intent)
    }

    private fun navigateToList() {
        val intent = Intent(this, FilmListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun navigateToEdit() {
        val intent = Intent(this, FilmEditActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun navigateToData(film: String) {
        val intent = Intent(this, FilmDataActivity::class.java)
        intent.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, film)
        startActivity(intent)
    }


}