package com.example.cele_n_baholo_poe.sampledata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.ActivityAddCategoryBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.example.cele_n_baholo_poe.models.serCategoryModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
        fireRef = FirebaseDatabase.getInstance("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Categories")


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
        val Num_collect = "0"


        if(Name.isNullOrEmpty()||Name.isBlank()){
            binding.edtTitle.error ="Enter a name"

        }
        if(Description.isNullOrEmpty()||Description.isBlank()){
            binding.edtDescription.error ="Enter a description"

        }

        if(Num_of_Desired.isNullOrEmpty()||Num_of_Desired.isBlank()){
            binding.edtNumItems.error ="Enter a number"

        }

        val CateId = fireRef.push().key!!

        val category = Categorys(CateId, Name, Description, Num_of_Desired,Num_collect)

        fireRef.child(CateId).setValue(category)
            .addOnCompleteListener{
                makeToasts("Glory to Christ The LORD")

                Intent(this, CategoryMain::class.java).also{
                    val categoryNew : serCategoryModel = serCategoryModel(CateId, Name, Description, Num_of_Desired,Num_collect)

                    startActivity(it)
                    it.putExtra("CategoryID",CateId)
                }
            }
            .addOnFailureListener{
                makeToasts("Some failure ${it.message}")
            }
    }

}