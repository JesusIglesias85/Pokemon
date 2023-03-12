package com.jiglgar.pokeapi.ui.pokemonDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jiglgar.pokeapi.R

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        viewModel = ViewModelProvider(this)[PokemonDetailViewModel::class.java]

        initUI()

    }

    private fun initUI(){
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonDetail(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->

            var tvName = findViewById<TextView>(R.id.tvDetailName)
            tvName.text = pokemon.name

            var tvHeight = findViewById<TextView>(R.id.tvDetailHeight)
            tvHeight.text = "Height: ${pokemon.height/10.0}m"

            var tvWeight = findViewById<TextView>(R.id.tvDetailWeight)
            tvWeight.text = "Weight: ${pokemon.weight/10.0}Kg"

            var tvBaseExperience = findViewById<TextView>(R.id.tvDetailBaseExperience)
            tvBaseExperience.text = "Base Experience: ${pokemon.baseExperience}"

            var imageView = findViewById<ImageView>(R.id.imageView)
            Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
        })
    }
}