package com.rick.and.morty.domain.model.character

data class Characters(val pageInformation: CharactersPageInfo, val characters: List<CharacterInformation>)