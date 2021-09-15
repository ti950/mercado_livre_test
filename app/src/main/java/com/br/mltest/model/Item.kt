package com.br.mltest.model

import java.io.Serializable

data class Item (
    val id: String,
    val title: String,
    val price: String,
    val thumbnail: String,
    val seller: Seller,
    val available_quantity: String,
    val sold_quantity: String,
    val condition: String,
    val installments: Installments,
    val address: Address
    ) : Serializable