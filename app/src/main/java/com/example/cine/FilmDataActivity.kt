package com.example.cine

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class FilmDataActivity : AppCompatActivity() {
    companion object{
        var EXTRA_FILM_TITLE="EXTRA_FILM_TITLE"
        const val REQUEST_EDIT_FILM  = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)
        val filmTitle=intent.getStringExtra(EXTRA_FILM_TITLE)
        val tvFilm: TextView =findViewById(R.id.tvFilm)
        tvFilm.text =filmTitle
        val btnRelatedFilm: Button =findViewById(R.id.btnRelatedFilm)
        val btnEditFilm:Button=findViewById(R.id.btnEditFilm)
        val btnBackMain:Button=findViewById(R.id.btnBackMain)

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