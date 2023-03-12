package com.jiglgar.pokeapi.ui.pokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jiglgar.pokeapi.R
import com.jiglgar.pokeapi.model.PokemonResult

class PokemonListAdapter(val pokemonClick: (Int) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {

    var pokemonList: List<PokemonResult> = emptyList()

    fun setData(list: List<PokemonResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        val pokemonText: TextView = holder.itemView.findViewById(R.id.pokemonText)
        pokemonText.text = pokemon.name.uppercase()

        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}