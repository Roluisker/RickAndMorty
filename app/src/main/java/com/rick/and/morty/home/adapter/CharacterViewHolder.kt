package com.rick.and.morty.home.adapter

import android.view.View
import com.rick.and.morty.core.BaseOnClickViewHolder
import com.rick.and.morty.databinding.CharacterListItemBinding

class CharacterViewHolder(val binding: CharacterListItemBinding) :
    BaseOnClickViewHolder(binding.root) {

    var itemClickListener: CharactersAdapter.CharacterTouchListener<CharacterViewHolder>? = null

    init {
        binding.root.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        itemClickListener?.onTouchCharacter(this)
    }
    
}