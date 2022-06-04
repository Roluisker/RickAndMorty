package com.rick.and.morty.domain

import androidx.lifecycle.LiveData
import com.rick.and.morty.data.CharactersRepository
import com.rick.and.morty.domain.model.CharacterInformation
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