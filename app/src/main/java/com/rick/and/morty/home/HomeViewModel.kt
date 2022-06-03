package com.rick.and.morty.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rick.and.morty.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {
    fun testFunction() {
        getCharactersUseCase()
    }
}