package com.example.cine

import android.app.Activity
import android.content.Intent
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
import androidx.core.graphics.drawable.toBitmap

class FilmEditActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_IMAGE_PICK=2
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
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Verifica que haya una app de cámara para manejar este intent
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
        btnSelectImage.setOnClickListener {
            val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageIntent.type = "image/*" // Filtrar para que solo se muestren imágenes

            if (pickImageIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(pickImageIntent, REQUEST_IMAGE_PICK)
            }
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


        if (titleFilm.isNotEmpty()) {
            intent.putExtra("EXTRA_FILM_TITLE", titleFilm)
        }
        if(directorFilm.isNotEmpty()){
            intent.putExtra("EXTRA_DIRECTOR_FILM", directorFilm)
        }
        if(yearFilm.isNotEmpty()){
            intent.putExtra("EXTRA_YEAR_FILM", yearFilm)
        }
        if(linkIMDB.isNotEmpty()){
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