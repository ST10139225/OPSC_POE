package com.example.cele_n_baholo_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import com.example.cele_n_baholo_poe.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {

    private lateinit var auth :FirebaseAuth

    private lateinit var fireRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        fireRef = Firebase.database("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("test")



        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl







            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
        }
        auth = FirebaseAuth.getInstance()

        binding.BtnLogout.setOnClickListener{
             Firebase.auth.signOut()
             finish()
         }


        binding.btnCategoryHome.setOnClickListener{

            fireRef.setValue("Praise Thee Lord").addOnCompleteListener{
                makeToasts("Added to test")
            }
        }

    }

    public fun makeToasts(text: String){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    }
}