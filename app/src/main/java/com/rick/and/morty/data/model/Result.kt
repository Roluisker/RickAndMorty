package com.rick.and.morty.data.model

import com.rick.and.morty.domain.model.CharacterInformation

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
        id = id.toString(),
        name = name,
        imageUrl = image,
        species = species
    )
}