package com.rick.and.morty.data.model.characters

import com.rick.and.morty.domain.model.character.CharacterInformation

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun Result.toCharacter(): CharacterInformation {
    return CharacterInformation(
        id = id,
        name = name,
        imageUrl = image,
        species = species,
        status = status,
        gender = gender,
        episodes = episode
    )
}