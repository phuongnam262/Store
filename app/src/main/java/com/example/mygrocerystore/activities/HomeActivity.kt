package com.example.mygrocerystore.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygrocerystore.MainActivity
import com.example.mygrocerystore.R
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var progressbar: ProgressBar
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        firebaseAuth = FirebaseAuth.getInstance()

        progressbar = findViewById(R.id.progressbar)
        progressbar.setVisibility(View.GONE)

        if (firebaseAuth.currentUser != null) {
            progressbar.setVisibility(View.VISIBLE)

            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Please Wait You are already login", Toast.LENGTH_SHORT).show()
            finish()

        }


    }

    fun login(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun registration(view: View) {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }
}