package com.rick.and.morty.domain

import com.rick.and.morty.data.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    operator fun invoke() {
        charactersRepository.testFunction()
    }
}