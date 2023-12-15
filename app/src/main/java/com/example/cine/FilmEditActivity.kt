package com.example.cine

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.core.graphics.drawable.toBitmap

class FilmEditActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_IMAGE_PICK=2
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val selectedFileUri = data?.data
                // Aquí puedes trabajar con el archivo seleccionado
            }
        }


    lateinit var ivPoster: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)

        ivPoster =findViewById(R.id.ivPoster)
        val btnTakePicture:Button=findViewById(R.id.btnTakePicture)
        val btnSelectImage:Button=findViewById(R.id.btnSelectImage)
        val etTitleFilm: EditText =findViewById(R.id.etTitleFilm)
        val etDirectorFilm: EditText =findViewById(R.id.etDirectorFilm)
        val etYearFilm: EditText =findViewById(R.id.etYearFilm)
        val etLinkIMDB: EditText =findViewById(R.id.etLinkIMDB)
        val spinnerGender: Spinner =findViewById(R.id.spinnerGender)
        val spinnerFormat: Spinner =findViewById(R.id.spinnerFormat)
        val btnSave: Button = findViewById(R.id.btnSave)
        val btnCancel: Button = findViewById(R.id.btnCancel)

        btnTakePicture.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                // Si no se tiene el permiso, solicítalo al usuario
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    1)
            } else {
                // Si ya se tiene el permiso, inicia la actividad de captura de imágenes
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }
            val intent =Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
        }
        btnSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            getContent.launch(intent)
        }
        btnSave.setOnClickListener { save(ivPoster,etTitleFilm,etDirectorFilm,etYearFilm,etLinkIMDB,spinnerGender,spinnerFormat) }
        btnCancel.setOnClickListener { cancel() }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // La imagen ha sido capturada, puedes acceder a ella aquí si lo deseas
            val imageBitmap = data?.extras?.get("data") as Bitmap

            // Haz algo con la imagen capturada (por ejemplo, mostrarla en un ImageView)
            ivPoster.setImageBitmap(imageBitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            // La imagen ha sido seleccionada
            val selectedImageUri: Uri? = data?.data

            // Haz algo con la imagen seleccionada, por ejemplo, muestra la imagen en un ImageView
            ivPoster.setImageURI(selectedImageUri)
        }
    }



    //Funcion que establece resultado a OK,Muestra un mensaje de guardado y te manda a la pagina principal
    private fun save(ivPoster:ImageView,etTitleFilm:EditText,etDirectorFilm:EditText,etYearFilm:EditText,etLinkIMDB:EditText,spinnerGender: Spinner,spinnerFormat: Spinner) {
        // Realizar las operaciones de guardado
        // Establecer el resultado y finalizar la actividad
        val titleFilm=etTitleFilm.text.toString()
        val directorFilm=etDirectorFilm.text.toString()
        val yearFilm=etYearFilm.text.toString()
        val linkIMDB=etLinkIMDB.text.toString()
        val gender:String=spinnerGender.selectedItem.toString()
        val format:String=spinnerFormat.selectedItem.toString()

        val intent= Intent(this,FilmDataActivity::class.java)

        val drawable = ivPoster.drawable
        if (drawable != null && drawable is BitmapDrawable) {
            val bitmap: Bitmap = drawable.bitmap
            if (!bitmap.isRecycled && bitmap.width > 0 && bitmap.height > 0){
                intent.putExtra("EXTRA_IMAGE",bitmap)
            }
        }


        if (titleFilm.isNotBlank()) {
            intent.putExtra("EXTRA_FILM_TITLE", titleFilm)
        }
        if(directorFilm.isNotBlank()){
            intent.putExtra("EXTRA_DIRECTOR_FILM", directorFilm)
        }
        if(yearFilm.isNotBlank()){
            intent.putExtra("EXTRA_YEAR_FILM", yearFilm)
        }
        if(linkIMDB.isNotBlank()){
            intent.putExtra("EXTRA_LINK_IMDB",linkIMDB)
        }
        if(gender!=R.id.spinnerGender.toString()){
            intent.putExtra("EXTRA_GENDER",gender)
        }
        if (format!=R.id.spinnerFormat.toString()){
            intent.putExtra("EXTRA_FORMAT",format)
        }
        startActivity(intent)
        setResult(Activity.RESULT_OK)
        Toast.makeText(this, R.string.successfully_saved, Toast.LENGTH_SHORT).show()
        finish()
    }

    //Funcion que establece resultado a CANCEL,Muestra un mensaje de cencelado y te manda a la pagina principal
    private fun cancel() {
        // Operaciones de cancelación
        // Establecer el resultado de cancelación y finalizar la actividad
        setResult(Activity.RESULT_CANCELED)
        Toast.makeText(this, R.string.process_cancelled, Toast.LENGTH_SHORT).show()
        finish()
    }

}