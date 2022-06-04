package com.rick.and.morty.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rick.and.morty.R
import com.rick.and.morty.databinding.CharacterListItemBinding
import com.rick.and.morty.domain.model.CharacterInformation
import com.squareup.picasso.Picasso

class CharactersAdapter(
    private val itemClickListenerOn: OnCharacterTouchListener
) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    private var characters: ArrayList<CharacterInformation> = ArrayList()

    fun addCharacters(characters: List<CharacterInformation>) {
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterListItemBinding.inflate(inflater, parent, false)
        val holder = CharacterViewHolder(binding)
        holder.itemClickListenerOn = itemClickListenerOn
        return holder
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.binding.character = item
            Picasso.get()
                .load(item.imageUrl)
                .placeholder(R.drawable.place_holder)
                .into(holder.binding.thumbnail)
        }
    }

    private fun getItem(index: Int): CharacterInformation? {
        return when {
            characters.size >= index -> characters[index]
            else -> null
        }
    }

    override fun getItemCount(): Int = characters.size

    interface OnCharacterTouchListener {
        fun onTouchCharacter(character: CharacterInformation?)
    }
}