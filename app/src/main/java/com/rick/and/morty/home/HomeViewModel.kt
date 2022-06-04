package com.rick.and.morty.home

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

    fun getAllCharacters() {
        viewModelScope.launch {
            val charactersResult = getCharactersUseCase(currentPage)
            if (!charactersResult.isNullOrEmpty()) {
                characters.postValue(ArrayList(charactersResult))
                currentPage++
            }
        }
    }

}