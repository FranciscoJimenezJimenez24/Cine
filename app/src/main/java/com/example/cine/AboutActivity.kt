package com.example.cine

import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.cine.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val btnGoToTheWebSide: Button = findViewById(R.id.btnGoToTheWebSide)
        val btnGetSupport: Button = findViewById(R.id.btnGetSupport)
        val btnBack: Button = findViewById(R.id.btnBack)

        var toastGoToTheWebSide: Button = findViewById(R.id.btnGoToTheWebSide)
        toastGoToTheWebSide.setOnClickListener {
            Toast.makeText(this, R.string.about_go_to_the_web_side, Toast.LENGTH_SHORT).show()
        }
        btnGoToTheWebSide.setOnClickListener { goToTheWebSide() }
        var toastGetSupport: Button = findViewById(R.id.btnGetSupport)
        toastGetSupport.setOnClickListener {
            Toast.makeText(this, R.string.about_get_support, Toast.LENGTH_SHORT).show()
        }
        btnGetSupport.setOnClickListener { getSupport() }
        var toastBack: Button = findViewById(R.id.btnBack)
        toastBack.setOnClickListener {
            Toast.makeText(this, R.string.about_back, Toast.LENGTH_SHORT).show()
        }
        btnBack.setOnClickListener { startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)) }
    }

    fun goToTheWebSide() {
        val intent = Intent(ACTION_VIEW, Uri.parse("https://www.youtube.com/"))
        startActivity(intent)
    }

    fun getSupport() {
        val emailIntent = Intent(ACTION_SENDTO, Uri.parse("mailto:3338213@iesplayamar.es"))
        startActivity(emailIntent)
    }
}