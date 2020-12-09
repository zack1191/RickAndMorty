package com.example.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.entities.Character

class ButtonAdapter : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>()
{
    private lateinit var characterList : List<Character>
    private var itemClickListener : CustomOnItemClickListener? = null

    inner class ButtonViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        fun bind(item : Character)
        {
            val buttonNo = itemView.findViewById<Button>(R.id.btnNumber)
            buttonNo.text = (adapterPosition + 1).toString()
            buttonNo.setOnClickListener {
                itemClickListener !!.customOnItemClick(buttonNo.text.toString().toInt())
            }
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ButtonViewHolder
    {
        return ButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false))
    }

    override fun onBindViewHolder(holder : ButtonViewHolder, position : Int)
    {
        holder.bind(characterList[position])
    }

    override fun getItemCount() : Int
    {
        return characterList.size / 5
    }

    fun setData(characterList : MutableList<Character>)
    {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    interface CustomOnItemClickListener
    {
        fun customOnItemClick(clickedButton : Int)
    }

    fun customSetOnItemClickListener(listener : CustomOnItemClickListener)
    {
        this.itemClickListener = listener
    }
}