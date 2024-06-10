package com.example.cele_n_baholo_poe.sampledata

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cele_n_baholo_poe.Adpater.RvCategoryAdpter
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.models.Categorys
import com.example.cele_n_baholo_poe.models.ItemModel
import com.example.cele_n_baholo_poe.models.serCategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddingCategory : AppCompatActivity() {

    private lateinit var  newRecy: RecyclerView
    private lateinit var RecylcerVAdapter : RvCategoryAdpter
//    private lateinit var newArrayf: ArrayList<serCategoryModel>

    private lateinit var  CategoryLists: ArrayList<Categorys>
    private lateinit var fireRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_main)


        newRecy = findViewById(R.id.Category_rc_List)
        newRecy.layoutManager = LinearLayoutManager(this)

        newRecy.setHasFixedSize(true)
        CategoryLists = arrayListOf()



        fireRef = Firebase.database("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Categories")

        fireRef.addValueEventListener( object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                CategoryLists.clear()

                for(categorysanp in snapshot.children){
                    val categoryitem = categorysanp.getValue(Categorys::class.java)
                    CategoryLists.add(categoryitem!!)
                    Log.d(TAG, "onChildAdded:" + snapshot.key!!)
                }

                CategoryLists.add(Categorys("asdfa","Food","My food", "7","0"))

                RecylcerVAdapter = RvCategoryAdpter(CategoryLists)

            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
        newRecy.adapter = RecylcerVAdapter


    }
}