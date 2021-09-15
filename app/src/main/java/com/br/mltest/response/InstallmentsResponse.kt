package com.br.mltest.response

import com.br.mltest.model.Installments
import com.br.mltest.model.Item
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InstallmentsResponse(
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "amount")
    val amount: Float
){
    fun getInstallmentsModel() = Installments(
        quantity = this.quantity,
        amount = this.amount
    )
}
