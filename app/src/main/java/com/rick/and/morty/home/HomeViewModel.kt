package com.rick.and.morty.home

import android.util.Log
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

    private var currentPage = 1
    val characters = MutableLiveData<ArrayList<CharacterInformation>>()
    var chars = ArrayList<CharacterInformation>()

    init {
        getAllCharacters()
    }

    fun getAllCharacters() {
        viewModelScope.launch {
            Log.d("Aloha", currentPage.toString())
            val charactersResult = getCharactersUseCase(currentPage)
            if (!charactersResult.isNullOrEmpty()) {
                chars.addAll(charactersResult)
                characters.postValue(chars)
                currentPage++
            }
        }
    }

}