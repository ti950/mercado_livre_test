package com.br.mltest.model

import java.io.Serializable

data class Address(
    val state_name: String,
    val city_name: String
): Serializable
