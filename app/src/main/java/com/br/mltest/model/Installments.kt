package com.br.mltest.model

import java.io.Serializable

data class Installments(
    val quantity: Int,
    val amount: Float,
): Serializable
