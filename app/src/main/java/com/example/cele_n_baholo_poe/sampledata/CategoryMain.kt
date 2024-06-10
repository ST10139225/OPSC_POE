package com.example.cele_n_baholo_poe.sampledata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cele_n_baholo_poe.Adpater.RvCategoryAdpter
import com.example.cele_n_baholo_poe.MainActivity
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.ActivityCategoryMainBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CategoryMain : AppCompatActivity() {

    private lateinit var binding : ActivityCategoryMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemList: ArrayList<Categorys>
    private lateinit var adapter: RvCategoryAdpter
    override fun onCreate(savedInstanceState: Bundle?) {
          binding = ActivityCategoryMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        database = FirebaseDatabase.getInstance("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Categories")

        recyclerView = findViewById(R.id.Category_rc_List)
        recyclerView.layoutManager = LinearLayoutManager(this)
        itemList = ArrayList()
        adapter = RvCategoryAdpter(itemList)
        recyclerView.adapter = adapter

        fetchCategoriesFromFirebase()

        binding.btnHome.setOnClickListener{
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnAdd.setOnClickListener{
            Intent(this, AddCategory::class.java).also {
                startActivity(it)
            }
        }

    }


    private fun fetchCategoriesFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                for (dataSnapshot in snapshot.children) {
                    val item = dataSnapshot.getValue(Categorys::class.java)
                    if (item != null) {
                        itemList.add(item)
                        Log.d("FirebaseData", "Item retrieved: ${item.name}, ${item.description}")
                    } else {
                        Log.d("FirebaseData", "Item is null")
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e("FirebaseError", "Database error: ${error.message}")
                makeToasts( "Failed to load data")
            }
        })
    }


    public fun makeToasts(text: String){
        makeText(this,text, Toast.LENGTH_LONG).show()

    }
}