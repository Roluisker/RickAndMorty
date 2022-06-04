package com.rick.and.morty.home.adapter

import android.view.View
import com.rick.and.morty.core.BaseOnClickViewHolder
import com.rick.and.morty.databinding.CharacterListItemBinding
import com.rick.and.morty.domain.model.CharacterInformation

class CharacterViewHolder(val binding: CharacterListItemBinding) :
    BaseOnClickViewHolder(binding.root) {

    var itemClickListenerOn: CharactersAdapter.OnCharacterTouchListener? = null

    init {
        binding.root.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        itemClickListenerOn?.onTouchCharacter(binding.character)
    }
    
}