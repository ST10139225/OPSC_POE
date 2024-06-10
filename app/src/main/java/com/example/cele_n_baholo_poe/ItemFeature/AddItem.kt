package com.example.cele_n_baholo_poe.ItemFeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.ActivityAddItemBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.example.cele_n_baholo_poe.models.ItemModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class AddItem : AppCompatActivity() {

    private lateinit var fireRef: DatabaseReference


    private lateinit var binding: ActivityAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityAddItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var acquisitionDate: Calendar
        var dateFrmCalView: String =""

        binding.aqDate.setOnDateChangeListener{ calView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            acquisitionDate =  Calendar.getInstance()
            acquisitionDate.set(year, month, dayOfMonth)
            calView.setDate(acquisitionDate.timeInMillis, true, true)
            dateFrmCalView = "$dayOfMonth/${month + 1}/$year"

        }


        fireRef = FirebaseDatabase.getInstance("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Items")

        binding.btnAdd.setOnClickListener{
            AddItems(dateFrmCalView)
        }
    }

    private fun AddItems( AqcDate : String){

        val Name = binding.edtTitle.text.toString()
        val Description  = binding.edtDescription.text.toString()
       var dateFrmCalView: String = AqcDate




        if(Name.isNullOrEmpty()||Name.isBlank()){
            binding.edtTitle.error ="Enter a name"

        }
        if(Description.isNullOrEmpty()||Description.isBlank()){
            binding.edtDescription.error ="Enter a description"

        }

        if(dateFrmCalView.isNullOrEmpty()||dateFrmCalView.isBlank()){
             binding.calLabel.error = "Please select a date !"

        }

        val ItemId = fireRef.push().key!!

         val New_Item =  ItemModel(ItemId,Name,Description,dateFrmCalView)

        fireRef.child(ItemId).setValue(New_Item)
            .addOnCompleteListener{
                makeToasts("Glory to Christ The Living LORD")
            }
            .addOnFailureListener{
                makeToasts("Some failure ${it.message}")
            }
    }

    fun makeToasts(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()

    }
}