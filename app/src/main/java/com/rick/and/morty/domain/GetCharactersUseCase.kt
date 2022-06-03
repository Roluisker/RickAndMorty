package com.rick.and.morty.domain

import com.rick.and.morty.data.CharactersRepository
import com.rick.and.morty.domain.model.CharacterInformation
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke() : List<CharacterInformation>  {
        return charactersRepository.getCharactersFromApi()
    }

}