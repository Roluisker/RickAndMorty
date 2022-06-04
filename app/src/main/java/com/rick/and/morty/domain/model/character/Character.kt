package com.rick.and.morty.domain.model.character

// The name avoid generate conflicts with the class called Character in Java
data class CharacterInformation(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val species: String,
    val status: String,
    val gender: String,
    val episodes: List<String>
)


