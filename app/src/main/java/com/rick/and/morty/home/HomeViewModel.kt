package com.rick.and.morty.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.and.morty.domain.GetCharactersUseCase
import com.rick.and.morty.domain.model.CharacterInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {

    val characters = MutableLiveData<ArrayList<CharacterInformation>>()

    fun getAllCharacters() {
        viewModelScope.launch {
            val charactersResult = getCharactersUseCase()
            characters.postValue(ArrayList(charactersResult))

            Log.d("Aloha", charactersResult[0].name)
        }
    }

}