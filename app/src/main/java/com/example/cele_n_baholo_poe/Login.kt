package com.example.cele_n_baholo_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cele_n_baholo_poe.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            var Email : String = binding.edtEmail.text.toString();
            var Password : String = binding.edtPassword.text.toString();

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

        }

    }

    fun makeToasts(text: String){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    }

}