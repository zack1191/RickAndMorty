package com.example.rickandmorty.ui.characters

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.repositories.CharacterRepository

class CharactersViewModel @ViewModelInject constructor(private val repository : CharacterRepository, @Assisted val savedStateHandle : SavedStateHandle) : ViewModel()
{

    val characters = repository.getCharacters()
    val getAllData = repository.getAllData()
    fun getSpecificData(from : Int, to : Int) : LiveData<List<Character>>
    {
        return repository.getSpecificData(from, to)
    }
}
