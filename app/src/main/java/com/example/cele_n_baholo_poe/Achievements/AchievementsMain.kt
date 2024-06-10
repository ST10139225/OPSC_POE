package com.example.cele_n_baholo_poe.Achievements

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.ActivityAchievementsMainBinding
import com.example.cele_n_baholo_poe.models.UserProfile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AchievementsMain : AppCompatActivity() {

    private lateinit var fireRef: DatabaseReference
    private lateinit var userPrf: UserProfile

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityAchievementsMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fireRef = FirebaseDatabase.getInstance("https://nk-inventory-26241-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Items")

        //val TotalItems: Int = userPrf.number_of_items.toString().toInt()
        var TotalItems  = 1;

        if(TotalItems>=1||TotalItems<=2){
            binding.firstItem.visibility = View.VISIBLE


        } else if(TotalItems>=3||TotalItems<=9){
            binding.card2.visibility = View.VISIBLE

        } else if(TotalItems>=10){
            binding.card3.visibility = View.VISIBLE

        }

    }

    private fun fetchUserProfile(itemId: String) {
        val query = fireRef.child("items").orderByChild("id").equalTo(itemId)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val profile = dataSnapshot.getValue(UserProfile::class.java)
                        if (profile != null) {
                            Log.d("FirebaseData", "Item retrieved: ${profile.id}, ${profile.number_of_items}")
                             userPrf = profile
                        }
                    }
                } else {
                    Log.d("FirebaseData", "No item found with id: $itemId")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Database error: ${error.message}")
                Toast.makeText(this@AchievementsMain, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}