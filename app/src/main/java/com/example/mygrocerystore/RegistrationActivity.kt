package com.example.mygrocerystore

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mygrocerystore.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegistrationActivity : AppCompatActivity() {
    private lateinit var signUp: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var signIn: TextView


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        
        signIn=findViewById(R.id.sign_in)
        signUp=findViewById(R.id.reg_btn)
        email=findViewById(R.id.email_reg)
        password=findViewById(R.id.password_reg)
        name=findViewById(R.id.name)

        signIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUp.setOnClickListener {
            createUser()
        }

    }

    private fun createUser() {
        val userEmail = email.text.toString()
        val userPassword = password.text.toString()
        val userName = name.text.toString()

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
            return
        }

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

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val userModel = UserModel(userName, userEmail, userPassword)
                    val userId = task.result?.user?.uid
                    database.getReference("Users").child(userId.toString()).setValue(userModel)


                    Toast.makeText(this, "Registration Successfully", Toast.LENGTH_SHORT).show()
                    // Bạn có thể chuyển qua màn hình chính ở đây nếu muốn
                } else {
                    val errorMessage = task.exception?.message ?: "Unknown error occurred"
                    Toast.makeText(this, "Registration Failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
    }

}




