package com.rick.and.morty.data.network

import com.rick.and.morty.data.model.CharactersModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersService @Inject constructor(private val api: RickAndMortyApiClient) {
    
    suspend fun getCharacters(): CharactersModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCharacters()
            response.body() ?: null
        }
    }

}