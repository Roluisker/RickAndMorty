package com.rick.and.morty.domain

import com.rick.and.morty.data.CharactersRepository
import com.rick.and.morty.domain.model.character.CharacterInformation
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke(characterId: Int): CharacterInformation? {
        return charactersRepository.getCharacterFromApi(characterId)
    }

}