package com.example.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.local.CharacterDao
import com.example.rickandmorty.data.network.CharacterDataSource
import com.example.rickandmorty.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
        private val remoteDataSource: CharacterDataSource,
        private val localDataSource: CharacterDao
                                             ) {


    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
                                             )

    fun getAllData():LiveData<List<Character>>{
        return localDataSource.getAllCharacters()
    }

    fun getSpecificData(from:Int,to:Int):LiveData<List<Character>>{
        return localDataSource.getSpecificData(from,to)
    }
}