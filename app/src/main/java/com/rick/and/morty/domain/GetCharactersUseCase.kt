package com.rick.and.morty.domain

import com.rick.and.morty.data.CharactersRepository
import com.rick.and.morty.domain.model.character.CharacterInformation
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke(page: Int) : List<CharacterInformation>  {
        val charactersResult = charactersRepository.getCharactersFromApi(page)

        return if(!charactersResult.isNullOrEmpty()) {
            charactersResult
        } else {
            emptyList()
        }
    }

}