package com.rick.and.morty.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.and.morty.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {

    fun getAllCharacters() {
        viewModelScope.launch {
            val characters = getCharactersUseCase()
            Log.d("Aloha", characters[0].name)
        }
    }

}