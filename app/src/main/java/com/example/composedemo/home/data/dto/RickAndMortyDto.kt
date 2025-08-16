package com.example.composedemo.home.data.dto


import com.example.composedemo.home.domain.model.Character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RickAndMortyDto(
    @SerialName("info")
    val info: Info,
    @SerialName("results")
    val charactersDto: List<CharacterDto>
)
@Serializable
data class Info(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("pages")
    val pages: Int,
    @SerialName("prev")
    val prev: String?
)
@Serializable
data class CharacterDto(
    @SerialName("created")
    val created: String,
    @SerialName("episode")
    val episode: List<String>,
    @SerialName("gender")
    val gender: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val location: LocationDto,
    @SerialName("name")
    val name: String,
    @SerialName("origin")
    val origin: OriginDto,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("url")
    val url: String
)
@Serializable
data class LocationDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
@Serializable
data class OriginDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)

fun CharacterDto.toCharacter(): Character {
    return Character( name, gender, image, location.name, origin.name, species, status, url)
}
