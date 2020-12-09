package com.example.rickandmorty.data.network

import javax.inject.Inject

class CharacterDataSource @Inject constructor(private val characterService : CharacterApi) : BaseDataSource()
{

    suspend fun getCharacters() =
            getResult { characterService.getAllCharacters() }


}