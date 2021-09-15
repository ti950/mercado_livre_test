package com.br.mltest.response

import com.br.mltest.model.Address
import com.br.mltest.model.Item
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemResultsResponse (
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "available_quantity")
    val available_quantity: String,
    @Json(name = "sold_quantity")
    val sold_quantity: String,
    @Json(name = "condition")
    val condition: String,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "seller")
    val seller: SellerResponse,
    @Json(name = "installments")
    val installments: InstallmentsResponse,
    @Json(name = "address")
    val address: AddressResponse
){
    fun getItemModel() = Item(
        id = this.id,
        title = this.title,
        price = this.price,
        thumbnail = this.thumbnail,
        seller = seller.getSellerModel(),
        available_quantity = this.available_quantity,
        sold_quantity = this.sold_quantity,
        condition = this.condition,
        installments = installments.getInstallmentsModel(),
        address = address.getAddressModel()
    )
}