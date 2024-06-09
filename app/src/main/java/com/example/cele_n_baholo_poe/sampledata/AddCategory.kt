package com.example.cele_n_baholo_poe.sampledata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.ActivityAddCategoryBinding

class AddCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding =  ActivityAddCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




    }
}