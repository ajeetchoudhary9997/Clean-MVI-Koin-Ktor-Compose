package com.example.composedemo.home.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable
import java.util.UUID


/**
 * Created by  Ajeet Singh on Date: 10/08/25.
 */
@Immutable
@Serializable
data class Character(
    val name: String,
    val gender: String,
    val image: String,
    val location: String,
    val origin: String,
    val species: String,
    val status: String,
    val url: String,
    val id: String= UUID.randomUUID().toString(),

    )