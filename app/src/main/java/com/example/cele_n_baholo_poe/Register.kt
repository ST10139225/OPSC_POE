package com.example.cele_n_baholo_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.cele_n_baholo_poe.databinding.ActivityRegisterBinding
import com.example.cele_n_baholo_poe.models.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var fireRef: DatabaseReference




    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityRegisterBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener{
            var Email : String = binding.edtEmail.text.toString();
            var Password : String = binding.edtPassword.text.toString();

            //initializing the auth
            auth = FirebaseAuth.getInstance()


            fireRef = FirebaseDatabase.getInstance("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("UserProfiles")



            binding.progBar.isVisible = true

            if (Email.isNullOrBlank()||Email.isNullOrEmpty()){
                makeToasts("Please enter a proper email address")
            } else if (Email.contains("@")==false){
                makeToasts("Please enter a proper email address")

            }else if (Password.isNullOrBlank()||Password.isNullOrEmpty()){
                makeToasts("Please enter a Password")
            }else if (Password.length<8){
                makeToasts("Please enter a Password that is 8 characters long")
            } else {

                auth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            makeToasts("createUserWithEmail:success")
                            binding.progBar.isVisible = false

                            val user = auth.currentUser

                            var uid :String =""
                            var userprof :UserProfile? = null
                            user?.let {
                                uid = it.uid

                                 userprof = UserProfile(uid,"0")
                            }

                            fireRef.child("Users").setValue(userprof)
                                .addOnCompleteListener{
                                    makeToasts("Add userprofile")


                                }
                                .addOnFailureListener{
                                    makeToasts("Some failure ${it.message}")
                                }

                            Intent(this, Login::class.java).also {
                                startActivity(it)
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }

            }

        }



    }

    public fun makeToasts(text: String){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    }


    private  fun reload() {
        super.onResume()
        this.onCreate(null)
    }
}