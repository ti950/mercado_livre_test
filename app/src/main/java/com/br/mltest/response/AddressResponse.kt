package com.br.mltest.response

import com.br.mltest.model.Address
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressResponse(
    @Json(name = "state_name")
    val state_name: String,
    @Json(name = "city_name")
    val city_name: String
){
    fun getAddressModel() = Address(
        state_name = this.state_name,
        city_name = this.city_name
    )
}
