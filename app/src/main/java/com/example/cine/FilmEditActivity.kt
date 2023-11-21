package com.example.cine

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class FilmEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)
        val btnSave:Button=findViewById(R.id.btnSave)
        val btnCancel:Button=findViewById(R.id.btnCancel)
        btnSave.setOnClickListener { save() }
        btnCancel.setOnClickListener { cancel() }

    }

    //Funcion que establece resultado a OK,Muestra un mensaje de guardado y te manda a la pagina principal
    private fun save() {
        // Realizar las operaciones de guardado
        // Establecer el resultado y finalizar la actividad
        setResult(Activity.RESULT_OK)
        Toast.makeText(this,R.string.successfully_saved, Toast.LENGTH_SHORT).show()
        finish()
    }


    //Funcion que establece resultado a CANCEL,Muestra un mensaje de cencelado y te manda a la pagina principal
    private fun cancel() {
        // Operaciones de cancelación
        // Establecer el resultado de cancelación y finalizar la actividad
        setResult(Activity.RESULT_CANCELED)
        Toast.makeText(this,R.string.process_cancelled, Toast.LENGTH_SHORT).show()
        finish()
    }

}