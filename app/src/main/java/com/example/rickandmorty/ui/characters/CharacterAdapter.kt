package com.example.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.R
import com.example.rickandmorty.data.entities.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>()
{
    private lateinit var characterList : List<Character>

    inner class CharacterViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        fun bind(item : Character)
        {
            val name = itemView.findViewById<TextView>(R.id.name)
            val species = itemView.findViewById<TextView>(R.id.species_and_status)
            val image = itemView.findViewById<ImageView>(R.id.image)

            name.text = item.name
            species.text = item.species
            Glide.with(image).load(item.image).transform(CircleCrop()).into(image)

        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : CharacterViewHolder
    {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder : CharacterViewHolder, position : Int)
    {
        holder.bind(characterList[position])
    }

    override fun getItemCount() : Int
    {
        return characterList.size
    }

    fun setData(characterList : MutableList<Character>)
    {
        this.characterList = characterList
        notifyDataSetChanged()
    }
}