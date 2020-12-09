package com.example.rickandmorty

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.ui.characters.ButtonAdapter
import com.example.rickandmorty.ui.characters.CharacterAdapter
import com.example.rickandmorty.ui.characters.CharactersViewModel
import com.example.rickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mAdapter : CharacterAdapter
    private lateinit var mButtonAdapter : ButtonAdapter
    private val viewModel : CharactersViewModel by viewModels()
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.characters.observe(this, Observer { it ->
            when (it.status)
            {
                Resource.Status.SUCCESS ->
                {
                    val filter = it.data !!.filter { it.id < 6 }
                    setupRecyclerView(filter as MutableList)
                    setupButtonRecyclerView(it.data as MutableList)
                }

                Resource.Status.ERROR ->
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING ->
                {

                }
            }
        })
        btnNext.setOnClickListener {
            rvButton.smoothScrollToPosition(4 + 4) // to move the next page to do this get the recyclerview last position and add with 4
        }
    }

    private fun setupRecyclerView(characterList : MutableList<Character>)
    {
        mAdapter = CharacterAdapter()
        rvCharacter.layoutManager = GridLayoutManager(applicationContext, 2)
        mAdapter.setData(characterList)
        rvCharacter.adapter = mAdapter

    }

    private fun setupButtonRecyclerView(characterList : MutableList<Character>)
    {
        mButtonAdapter = ButtonAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvButton.layoutManager = layoutManager
        mButtonAdapter.setData(characterList)
        rvButton.adapter = mButtonAdapter

        mButtonAdapter.customSetOnItemClickListener(object : ButtonAdapter.CustomOnItemClickListener
        {
            override fun customOnItemClick(clickedButton : Int)
            {

                viewModel.getSpecificData(clickedButton, clickedButton * 5).observe(this@MainActivity, Observer {
                    val sortedList = it.sortedBy { it -> it.id }
                    mAdapter.setData(sortedList as MutableList)
                })
            }

        })

    }
}