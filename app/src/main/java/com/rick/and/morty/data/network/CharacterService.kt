package com.rick.and.morty.data.network

import com.rick.and.morty.data.model.characters.CharactersModel
import com.rick.and.morty.data.model.characters.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val api: RickAndMortyApiClient) {

    suspend fun getCharacters(page: Int): CharactersModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCharacters(page)
            response.body() ?: null
        }
    }

    suspend fun getCharacter(characterId: Int): Result? {
        return withContext(Dispatchers.IO) {
            val response = api.getCharacter(characterId)
            response.body() ?: null
        }
    }

}