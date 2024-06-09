package com.example.cele_n_baholo_poe.sampledata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.ActivityAddCategoryBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import java.util.Locale.Category

class AddCategory : AppCompatActivity() {

    private lateinit var fireRef: DatabaseReference
    private lateinit var binding : ActivityAddCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {

          binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fireRef = Firebase.database("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("test")

        binding.btnAdd.setOnClickListener {

            AddCat()



        }


    }
    fun makeToasts(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()

    }


    private fun AddCat(){

        val Name = binding.edtTitle.text.toString()
        val Description  = binding.edtDescription.text.toString()
        val Num_of_Desired = binding.edtNumItems.text.toString()
        val Num_collect = 0

        val CateId = fireRef.push().key!!

        val category = Categorys(CateId, Name, Description, Num_of_Desired)

        fireRef.child(CateId).setValue(category)
            .addOnCompleteListener{
                makeToasts("Glory to Christ The LORD")
            }
            .addOnFailureListener{
                makeToasts("Some failure ${it.message}")
            }
    }

}