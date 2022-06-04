package com.rick.and.morty.character_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.and.morty.domain.GetCharacterUseCase
import com.rick.and.morty.domain.model.CharacterInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase) :
    ViewModel() {

    val character = MutableLiveData<CharacterInformation>()
    val isLoading = MutableLiveData(false)

    fun getCharacter(characterId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val characterResult = getCharacterUseCase(characterId)
            characterResult?.let {
                character.postValue(it)
            } ?: run {
                Log.d("Aloha", "null")
            }
            isLoading.postValue(false)
        }
    }

}