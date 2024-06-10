package com.example.cele_n_baholo_poe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.view.isVisible
import com.example.cele_n_baholo_poe.databinding.ActivityLoginBinding
import com.example.cele_n_baholo_poe.sampledata.AddCategory
import com.example.cele_n_baholo_poe.sampledata.AddingCategory
import com.example.cele_n_baholo_poe.sampledata.CategoryMain
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        auth = FirebaseAuth.getInstance()
        auth.signOut()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityLoginBinding.inflate(layoutInflater)
//        super.onCreate(savedInstanceState)
        setContentView(binding.root)

         ;

        binding.btnLogin.setOnClickListener{
            var Email : String = binding.edtEmail.text.toString();
            var Password : String = binding.edtPassword.text.toString();
            Email = "kokib777@gmail.com"
            Password = "123Kokib7777"

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


            ////To hand the login in


            auth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        makeToasts( "signInWithEmail:success")
                        binding.progBar.isVisible= false
                        val user = auth.currentUser

                        Intent(this, AddCategory::class.java).also{
                            startActivity(it)
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                       makeToasts( "signInWithEmail:failure")
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        super.onCreate(savedInstanceState)
        



        binding.LnkRegister.setOnClickListener{
                Intent(this,Register::class.java).also {
                    startActivity(it)
                }

        }

    }

   public fun makeToasts(text: String){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    }


}