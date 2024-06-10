package com.example.cele_n_baholo_poe.Adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.RvCategoryItemBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.example.cele_n_baholo_poe.models.ItemModel

class RvCategoryAdpter(private val CategoryLists: ArrayList<Categorys>) : RecyclerView.Adapter<RvCategoryAdpter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = RvCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  CategoryLists.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = CategoryLists[position]

        holder.apply {
            binding.apply {
                lblName.text = currentItem.Name
                lblDescription.text = currentItem.Description
                lblDesiredNum.text = currentItem.Num_of_Desired
                lblCollected.text = currentItem.Num_collect
            }
        }

    }


    class MyViewHolder( val binding: RvCategoryItemBinding): RecyclerView.ViewHolder(binding.root){

    }
}