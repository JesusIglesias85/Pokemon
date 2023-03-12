package com.jiglgar.pokeapi.ui.pokemonList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jiglgar.pokeapi.R
import com.jiglgar.pokeapi.ui.pokemonDetail.PokemonDetailActivity

class PokemonListActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]

        initUI()
    }

    private fun initUI() {

        var rvPokemonList = findViewById<RecyclerView>(R.id.rvPokemonList)

        rvPokemonList.layoutManager = LinearLayoutManager(this)

        rvPokemonList.adapter = PokemonListAdapter {
            val intent = Intent(this, PokemonDetailActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this, Observer { list ->
            (rvPokemonList.adapter as PokemonListAdapter).setData(list)
        })
    }
}