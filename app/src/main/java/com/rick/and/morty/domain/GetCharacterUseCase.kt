package com.rick.and.morty.domain

import com.rick.and.morty.data.CharactersRepository
import com.rick.and.morty.domain.model.CharacterInformation
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val CharacterInformation: CharactersRepository) {

    suspend operator fun invoke(characterId: Int): CharacterInformation? {
        return CharacterInformation.getCharacterFromApi(characterId)
    }

}