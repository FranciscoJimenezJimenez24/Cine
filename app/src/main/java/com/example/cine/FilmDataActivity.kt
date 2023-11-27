package com.example.cine

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class FilmDataActivity : AppCompatActivity() {
    companion object{
        var EXTRA_FILM_TITLE="EXTRA_FILM_TITLE"
        val EXTRA_DIRECTOR="EXTRA_DIRECTOR"
        val EXTRA_POSTER_ID="EXTRA_POSTER_ID"
        val EXTRA_GENDER="EXTRA_GENDER"
        val EXTRA_FORMAT="EXTRA_FORMAT"
        val EXTRA_YEAR="EXTRA_YEAR"
        val EXTRA_LINK_IMDB="EXTRA_LINK_IMDB"
        const val REQUEST_EDIT_FILM  = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)

        val filmTitle=intent.getStringExtra(EXTRA_FILM_TITLE)
        val director=intent.getStringExtra(EXTRA_DIRECTOR)
        val poster_id=intent.getIntExtra(EXTRA_POSTER_ID,0)
        val gender=intent.getStringExtra(EXTRA_GENDER)
        val format=intent.getStringExtra(EXTRA_FORMAT)
        val year=intent.getIntExtra(EXTRA_YEAR,0)
        val linkIMDB=intent.getStringExtra(EXTRA_LINK_IMDB)

        val tvFilm: TextView =findViewById(R.id.tvFilm)
        val tvDirector:TextView=findViewById(R.id.tvDirector)
        val ivPoster: ImageView =findViewById(R.id.ivPoster)
        val tvGender:TextView=findViewById(R.id.tvGender)
        val tvFormat:TextView=findViewById(R.id.tvFormat)
        val tvYear:TextView=findViewById(R.id.tvYear)
        val tvIMDB:TextView=findViewById(R.id.tvIMDB)

        tvDirector.text=director
        tvFilm.text =filmTitle
        ivPoster.setImageResource(poster_id)
        tvGender.text=gender
        tvFormat.text=format
        tvYear.id=year
        tvIMDB.text=linkIMDB

        val btnRelatedFilm: Button =findViewById(R.id.btnRelatedFilm)
        val btnEditFilm:Button=findViewById(R.id.btnEditFilm)
        val btnBackMain:Button=findViewById(R.id.btnBackMain)

        tvIMDB.setOnClickListener {
            if (linkIMDB != null) {
                openIMDbLink(tvIMDB,linkIMDB)
            }
        }
        btnRelatedFilm.setOnClickListener {
            if (filmTitle!=null){
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
                val textViewChangesResult:TextView = findViewById(R.id.tvChange)
                textViewChangesResult.text = getString(R.string.saved_changes)
            } else if (resultCode == RESULT_CANCELED) {
                val textViewChangesResult:TextView = findViewById(R.id.tvChange)
                textViewChangesResult.text = getString(R.string.unsaved_changes)
            }
        }
    }

    private fun openIMDbLink(view: View,link:String) {
        val imdbUrl =link // Reemplaza con el enlace de la película en IMDb

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(imdbUrl)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun navigateToList() {
        val intent= Intent(this,FilmListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun navigateToEdit() {
        val intent= Intent(this,FilmEditActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun navigateToData(film:String) {
        val intent= Intent(this,FilmDataActivity::class.java)
        intent.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, film)
        startActivity(intent)
    }




}