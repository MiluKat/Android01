package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemPersonajeBinding
import com.example.myapplication.model.Personaje
import com.squareup.picasso.Picasso

class PersonajeAdapter(val lstPersonaje: List<Personaje>) : RecyclerView.Adapter<PersonajeAdapter.PersonajeAdapterViewHolder>(){
    class PersonajeAdapterViewHolder(val binding: ItemPersonajeBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeAdapterViewHolder {
        val binding = ItemPersonajeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonajeAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajeAdapterViewHolder, position: Int) {
        val rst = lstPersonaje[position]
        holder.binding.txtName.text = rst.name
        holder.binding.txtSpecies.text = rst.species
        holder.binding.txtAncestry.text = rst.ancestry
        holder.binding.txtActor.text = rst.actor
        Picasso.get()
            .load(rst.image)
            .into(holder.binding.imgPersonaje)
    }

    override fun getItemCount(): Int {
        return lstPersonaje.size
    }
}