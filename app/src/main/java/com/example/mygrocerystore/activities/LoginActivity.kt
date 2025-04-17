package com.example.mygrocerystore.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mygrocerystore.R
import com.google.firebase.auth.FirebaseAuth

open class LoginActivity : AppCompatActivity() {
    private lateinit var signIn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUp: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        progressBar = findViewById(R.id.progressbar)
        progressBar.setVisibility(View.GONE)

        firebaseAuth = FirebaseAuth.getInstance()

        signIn=findViewById(R.id.login_btn)
        email=findViewById(R.id.email_login)
        password=findViewById(R.id.password_login)
        signUp=findViewById(R.id.sign_up)

        signUp.setOnClickListener{
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
        }

        signIn.setOnClickListener{
                loginUser()
                progressBar.setVisibility(View.VISIBLE)
        }
    }

    private fun loginUser() {
        val userEmail = email.text.toString()
        val userPassword = password.text.toString()



        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            return
        }

        if (userPassword.length < 6) {
            Toast.makeText(this, "Password must be greater than 6 letters", Toast.LENGTH_SHORT).show()
            return
        }

        //loginUser
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->if (task.isSuccessful) {
                progressBar.setVisibility(View.GONE)
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            }else{
                progressBar.setVisibility(View.GONE)
                Toast.makeText(this, "Login Failed"+task.isSuccessful, Toast.LENGTH_SHORT).show()
            }
            }
    }
}