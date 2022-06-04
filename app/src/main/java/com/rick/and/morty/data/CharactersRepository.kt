package com.rick.and.morty.data

import com.rick.and.morty.data.model.CharactersModel
import com.rick.and.morty.data.model.toCharacter
import com.rick.and.morty.data.network.CharactersService
import com.rick.and.morty.domain.model.CharacterInformation
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val api: CharactersService) {

    suspend fun getCharactersFromApi(page: Int): List<CharacterInformation> {
        val charactersModel: CharactersModel? = api.getCharacters(page)
        val characters = mutableListOf<CharacterInformation>()

        charactersModel?.results?.forEach { it ->
            characters.add(it.toCharacter())
        }

        return characters
    }

    suspend fun getCharacterFromApi(characterId: Int): CharacterInformation? {
        val character = api.getCharacter(characterId)
        return character?.toCharacter() ?: null
    }

}