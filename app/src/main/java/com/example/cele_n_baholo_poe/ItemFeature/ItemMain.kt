package com.example.cele_n_baholo_poe.ItemFeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cele_n_baholo_poe.Adpater.RvCategoryAdpter
import com.example.cele_n_baholo_poe.Adpater.RvItemAdpter
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.ActivityCategoryMainBinding
import com.example.cele_n_baholo_poe.databinding.ActivityItemMainBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.example.cele_n_baholo_poe.models.ItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ItemMain : AppCompatActivity() {

    private lateinit var binding : ActivityItemMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemList: ArrayList<ItemModel>
    private lateinit var adapter: RvItemAdpter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItemMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_main)

        // Initialize Firebase with the specific database URL
        database = FirebaseDatabase.getInstance("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Items")

        // Set up RecyclerView
        recyclerView = findViewById(R.id.rc_ItemsView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        itemList = ArrayList()
        adapter = RvItemAdpter(itemList)
        recyclerView.adapter = adapter

        // Fetch data from Firebase
        fetchDataFromFirebase()
    }


    private fun fetchDataFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                for (dataSnapshot in snapshot.children) {
                    val item = dataSnapshot.getValue(ItemModel::class.java)
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

            }
        })
    }
}