package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRickBinding
import com.example.myapplication.model.Results
import com.squareup.picasso.Picasso

class RickAdapter(val lstRick: List<Results>) : RecyclerView.Adapter<RickAdapter.RickAdapterViewHolder>(){
    class RickAdapterViewHolder(val binding: ItemRickBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAdapterViewHolder {
        val binding = ItemRickBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RickAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RickAdapterViewHolder, position: Int) {
        val rst = lstRick[position]
        holder.binding.txtNombre.text =rst.name
        holder.binding.txtStatus.text =rst.status
        holder.binding.txtId.text = rst.id.toString()
        Picasso.get()
            .load(rst.image)
            .into(holder.binding.img)
    }

    override fun getItemCount(): Int {
        return lstRick.size
    }
}