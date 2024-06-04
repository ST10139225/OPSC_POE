package com.example.cele_n_baholo_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.cele_n_baholo_poe.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
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

            binding.progBar.isVisible = true

            if (Email.isNullOrBlank()||Email.isNullOrEmpty()){
                makeToasts("Please enter a proper email address")
            } else if (Email.contains("@")==false){
                makeToasts("Please enter a proper email address")

            }
            if (Password.isNullOrBlank()||Password.isNullOrEmpty()){
                makeToasts("Please enter a Password")
            }else if (Password.length<8){
                makeToasts("Please enter a Password that is 8 characters long")
            }

            auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        makeToasts("createUserWithEmail:success")
                        binding.progBar.isVisible = false

                        val user = auth.currentUser

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

        binding.LnkLogin.setOnClickListener{
            Intent(this,Login::class.java).also{
                startActivity(it)
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