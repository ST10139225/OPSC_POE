package com.example.cele_n_baholo_poe.Adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.RvCategoryItemBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.example.cele_n_baholo_poe.models.ItemModel

class RvCategoryAdpter(private val CategoryLists: ArrayList<Categorys>) : RecyclerView.Adapter<RvCategoryAdpter.MyViewHolder>() {


    class MyViewHolder( val binding: RvCategoryItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(RvCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return  CategoryLists.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = CategoryLists[position]
        holder.binding.lblId.text ="ID: ${currentItem.id}"
        holder.binding.lblName.text = "Name: ${currentItem.name}"
                holder.binding.lblDescription.text = "Description: ${currentItem.description}"
                holder.binding.lblDesiredNum.text = "Desired Num: ${currentItem.num_of_Desired}"
                holder.binding.lblCollected.text = "Collected: ${currentItem.num_collect}"


    }


}