package com.rick.and.morty.domain.model

data class Characters(val pageInformation: CharactersPageInfo, val characters: List<CharacterInformation>)