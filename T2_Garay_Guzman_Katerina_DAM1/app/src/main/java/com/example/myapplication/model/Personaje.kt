package com.example.myapplication.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Personaje (

    @SerialName("name"             ) var name            : String?           = null,
    @SerialName("species"          ) var species         : String?           = null,
    @SerialName("ancestry"         ) var ancestry        : String?           = null,
    @SerialName("actor"            ) var actor           : String?           = null,
    @SerialName("image"            ) var image           : String?           = null

)