package com.br.mltest.response

import com.br.mltest.model.Seller
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SellerResponse (
    @Json(name = "permalink")
    val permalink: String,
    @Json(name = "registration_date")
    val registration_date: String
){
    fun getSellerModel() = Seller(
        permalink = this.permalink,
        registration_date = this.registration_date
    )
}