package com.example.cele_n_baholo_poe.Adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cele_n_baholo_poe.R
import com.example.cele_n_baholo_poe.databinding.RcItemViewBinding
import com.example.cele_n_baholo_poe.databinding.RvCategoryItemBinding
import com.example.cele_n_baholo_poe.models.Categorys
import com.example.cele_n_baholo_poe.models.ItemModel

class RvItemAdpter(private val ItemList: ArrayList<ItemModel>) : RecyclerView.Adapter<RvItemAdpter.MyViewHolder>() {


    class MyViewHolder( val binding: RcItemViewBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(RcItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return  ItemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = ItemList[position]
        holder.binding.itemId.text = currentItem.id
        holder.binding.itemName.text = currentItem.name
        holder.binding.itemDescription.text = currentItem.description
        holder.binding.itemName.text = currentItem.acquisition_Date
        holder.binding.rcContainerItem.setOnClickListener{

        }



    }


}