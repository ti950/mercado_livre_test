package com.br.mltest.viewmodel

import com.br.mltest.model.Item

sealed class ItemResult {
    class Success(val items: List<Item>) : ItemResult()
    class ApiError(val statusCode: Int) : ItemResult()
    object ServerError: ItemResult()
}