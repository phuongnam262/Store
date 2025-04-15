package com.example.mygrocerystore

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

public class LoginActivity : AppCompatActivity() {
    private lateinit var signIn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUp: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        signIn=findViewById(R.id.login_btn)
        email=findViewById(R.id.email_login)
        password=findViewById(R.id.password_login)
        signUp=findViewById(R.id.sign_up)

        signUp.setOnClickListener{
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
        }

        signIn.setOnClickListener{
            fun onClick(view: View ) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}


