package com.rick.and.morty.data

import com.rick.and.morty.data.model.characters.CharactersModel
import com.rick.and.morty.data.model.characters.toCharacter
import com.rick.and.morty.data.network.CharacterService
import com.rick.and.morty.domain.model.character.CharacterInformation
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val api: CharacterService) {

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