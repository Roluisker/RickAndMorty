package com.rick.and.morty.character_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.and.morty.domain.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase) :
    ViewModel() {

    fun getCharacter(characterId: Int) {
        viewModelScope.launch {
            val character = getCharacterUseCase(characterId)
            if (character != null) {
                Log.d("Aloha", character?.name)
            }
        }
    }

}