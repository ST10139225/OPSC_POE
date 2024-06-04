package com.example.cele_n_baholo_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cele_n_baholo_poe.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
         binding.BtnLogout.setOnClickListener{
             Firebase.auth.signOut()

             binding.HomeMSg.text ="Praise The LORD"
         }




    }
}